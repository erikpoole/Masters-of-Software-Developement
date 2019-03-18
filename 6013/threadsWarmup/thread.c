#include <stdio.h>
#include <assert.h>
#include <inttypes.h>
#include <sys/mman.h>
#include <stdlib.h>

#include "thread.h"

// Format strings like printf use a fair bit of stack space.
// With a stack much smaller than this any printf's in our
// threads will cause strange segfaults.
static const size_t STACK_SIZE =  32768;// 16384;

typedef struct context {
    uint64_t r15;
    uint64_t r14;
    uint64_t r13;
    uint64_t r12;
    uint64_t rbp;
    uint64_t rbx;
    uint64_t rip;
} __attribute__((packed)) context_t;

void swtch(context_t** old, context_t* new);

thread_t* running;
thread_t* runnable;
thread_t* exited;
thread_t main_thread;

void dump_thread(const thread_t* thread)
{
    if (!thread) {
        fprintf(stderr, "NULL thread\n");
        return;
    }

    const context_t* context = thread->context;
    if (!context) {
        fprintf(stderr, "NULL context\n");
    } else {
        fprintf(stderr,
            "running\n"
            "r15: %llx\n"
            "r14: %llx\n"
            "r13: %llx\n"
            "r12: %llx\n"
            "rbp: %llx\n"
            "rbx: %llx\n"
            "rip: %llx\n",
            context->r15,
            context->r14,
            context->r13,
            context->r12,
            context->rbp,
            context->rbx,
            context->rip);
    }
}

thread_t* thread_for_context(context_t* context) {
    if (!context)
        return NULL;
    return (thread_t*)((uintptr_t)context - sizeof(thread_t) + sizeof(context_t*));
}

void thread_switch_to(thread_t* next) {
    thread_t* current = running;
    running = next;
    swtch(&current->context, next->context);
}

void thread_push(thread_t** list, thread_t* thread)
{
    thread->next = NULL;

    if (!*list) {
        *list = thread;
    } else {
        thread_t* pred = *list;
        while (pred->next)
            pred = pred->next;
        pred->next = thread;
    }
}

thread_t* thread_pop(thread_t** list)
{
    thread_t* r = *list;
    *list = r->next;
    r->next = NULL;
    return r;
}

int thread_unlink(thread_t** list, thread_t* thread)
{
    thread_t* t = *list;
    if (t == thread) {
        *list = t->next;
        return 1;
    } else {
        while (1) {
            if (!t)
                return 0;
            if (t->next == thread) {
                t->next = thread->next;
                return 1;
            }
            t = t->next;
        }
    }

    assert(0);
}

int dump_runnable()
{
    fprintf(stderr, "Runnable thread list:\n");
    thread_t* t = runnable;
    while (t) {
        dump_thread(t);
        t = t->next;
    }
	return 0;
}

int thread_yield()
{
    thread_t* to_run = thread_pop(&runnable); // Pick something out to run.
    if (!to_run) // Nothing else to run.
        return 0;

    thread_push(&runnable, running); // Put us on the runnable list.
    thread_switch_to(to_run);

    return 0;
}

void* thread_join(thread_t* t) {
    while (1) {
        int success = thread_unlink(&exited, t);
        if (success)
            return t->ret;
        else
            thread_yield();
    }
    assert(0);
    return NULL;
}

static void thread_exit() {
    thread_t* to_run = thread_pop(&runnable);
    if (!to_run) // Nothing else to run.
        exit(0);

    thread_push(&exited, running);
    thread_switch_to(to_run);

    assert(0); // Unreachable.
}

static void thread_entry() {
  printf("thread entry\n");
    running->ret = running->start_routine(running->arg);
    thread_exit();
}

int thread_create(thread_t* thread,
                  void* (*start_routine)(void*),
                  void* arg)
{
    assert(!thread->next);
    assert(!thread->stack);
    assert(!thread->context);

    void* p = mmap(NULL,
                   STACK_SIZE,
                   PROT_READ | PROT_WRITE,
                   MAP_PRIVATE | MAP_ANONYMOUS,
                   -1, 0);
    assert(p != MAP_FAILED);
    thread->stack = p;

    thread->start_routine = start_routine;
    thread->arg = arg;
    thread->ret = NULL;
    thread->context = thread->stack + STACK_SIZE - sizeof(context_t) - 8;
    fprintf(stderr, "Stack from %p to %p with context starting at %p\n",
            thread->stack, thread->stack+STACK_SIZE, thread->context);

    thread->context->r12 = 0;
    thread->context->r13 = 0;
    thread->context->r14 = 0;
    thread->context->r15 = 0;
    thread->context->rbp = 0;
    thread->context->rbx = 0;
    thread->context->rip = (uint64_t)thread_entry;

    thread->next = NULL;
    thread_push(&runnable, thread);

    thread_yield();

    return 0;
}

int thread_init()
{
    main_thread.stack = NULL;
    main_thread.next = NULL;
    main_thread.context = NULL;

    running = &main_thread;
    runnable = NULL;
    exited = NULL;
    return 0;
}


struct context;

typedef struct thread {
    struct thread* next;

    void* (*start_routine)(void*);
    void *arg;
    void *ret;

    void* stack;
    struct context* context;
} thread_t;

int thread_init();

int thread_create(thread_t* thread,
                  void* (*start_routine)(void*),
                  void* arg);
int thread_yield();
void* thread_join(thread_t* t);

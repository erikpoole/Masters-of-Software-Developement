#include <stdio.h>
#include <assert.h>
#include <inttypes.h>
#include <string.h>
#include <stdlib.h>

#include "thread.h"

void* start(void* arg) {
    for (int i = 0; i < 10; ++i) {
        fprintf(stderr, "Hello, World from %s thread.\n", (char*)arg);
        thread_yield();
    }
    return arg;
}

int main(int argc, char* argv[]) {
    thread_init();

    int r = 0;
    thread_t t1, t2;
    memset(&t1, 0, sizeof(t1));
    memset(&t2, 0, sizeof(t2));

    thread_create(&t1, start, "first");
    assert(!r);
    thread_create(&t2, start, "second");
    assert(!r);

    start("main");

    void* ret = thread_join(&t1);
    fprintf(stderr, "t1 said %s\n", (char*)ret);

    ret = thread_join(&t2);
    fprintf(stderr, "t2 said %s\n", (char*)ret);

    return 0;
}

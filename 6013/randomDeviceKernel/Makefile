obj-m := myRand.o
KDIR := /lib/modules/$(shell uname -r)/build
PWD := $(shell pwd)

all:
	$(MAKE) -C $(KDIR) M=$(PWD) modules

mount:
	sudo insmod myRand.ko

unmount: 
	sudo rmmod myRand.ko

clean:
	$(MAKE) -C $(KDIR) M=$(PWD) clean

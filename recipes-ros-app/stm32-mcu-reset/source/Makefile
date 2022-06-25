# ========================================================================
#
#  Copyright (C) 2022 by duvallee
#  Makefile
#
#  ========================================================================

# -----------------------------------------------------------
TOPDIR := $(shell /bin/pwd)
OBJECT_DIR := $(TOPDIR)/output

# -----------------------------------------------------------
NO_GDB_DEBUGGING = 1

# -----------------------------------------------------------
TARGET := stm32-mcu-reset 

# -----------------------------------------------------------
PATH_INCLUDE := -I$(TOPDIR)/inc

# -----------------------------------------------------------
CFLAGS := -Wall
CFLAGS += -fopenmp
CFLAGS += -Wstrict-prototypes
CFLAGS += -Wno-trigraphs
CFLAGS += -fno-strict-aliasing -fno-common
CFLAGS += -O2 -g
CFLAGS += ${PATH_INCLUDE}

# -----------------------------------------------------------
# Flasgs for C++
CXXFLAGS := -Wall
# CXXFLAGS += -Wl,--stack,0x100000000
# CXXFLAGS += -Wl,--heap,0x100000000
CXXFLAGS += -std=c++11
CXXFLAGS += -Wno-trigraphs
CXXFLAGS += -fno-strict-aliasing -fno-common
# CXXFLAGS += -fexceptions
# CXXFLAGS += -pthread
# CXXFLAGS += -fopenmp
ifeq (${NO_GDB_DEBUGGING}, 1)
	CXXFLAGS += -O3
else
	CXXFLAGS += -O0 -g
	CXXFLAGS += -DNO_GDB_DEBUGGING
endif

# -----------------------------------------------------------
# Flasgs for Linker
LDFLAGS := -L /usr/local/lib
LDFLAGS += -lmipi_camera

# -----------------------------------------------------------
# C++ Soruce File
CXX_SRC := ${TOPDIR}/main.cpp

# -----------------------------------------------------------
BJS :=
OBJS += $(C_SRC:%.c=$(OBJECT_DIR)/%.o)
OBJS += $(ASM_SRC:%.s=$(OBJECT_DIR)/%.o)
OBJS += $(CXX_SRC:%.cpp=$(OBJECT_DIR)/%.o)

# -----------------------------------------------------------
.PHONY: $(TARGET) clean install

# -----------------------------------------------------------
# all : param_display $(TARGET)
all : $(TARGET)

# -----------------------------------------------------------
$(TARGET): $(OBJS)
	$(CXX) $(OBJS) $(LDFLAGS) -o ${OBJECT_DIR}/$(TARGET)

# -----------------------------------------------------------
clean:
	rm -rf $(OBJECT_DIR)

# -----------------------------------------------------------
#  Implicit targets
$(OBJECT_DIR)/%.o: %.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) -c $< -o $@

$(OBJECT_DIR)/%.o: %.cpp
	mkdir -p $(dir $@)
	$(CXX) $(CXXFLAGS) -c $< -o $@



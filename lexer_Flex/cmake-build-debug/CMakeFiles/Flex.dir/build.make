# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/adelinazagitova/CLionProjects/Flex

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Flex.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Flex.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Flex.dir/flags.make

CMakeFiles/Flex.dir/main.c.o: CMakeFiles/Flex.dir/flags.make
CMakeFiles/Flex.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/Flex.dir/main.c.o"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/Flex.dir/main.c.o   -c /Users/adelinazagitova/CLionProjects/Flex/main.c

CMakeFiles/Flex.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/Flex.dir/main.c.i"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/adelinazagitova/CLionProjects/Flex/main.c > CMakeFiles/Flex.dir/main.c.i

CMakeFiles/Flex.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/Flex.dir/main.c.s"
	/Library/Developer/CommandLineTools/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/adelinazagitova/CLionProjects/Flex/main.c -o CMakeFiles/Flex.dir/main.c.s

# Object files for target Flex
Flex_OBJECTS = \
"CMakeFiles/Flex.dir/main.c.o"

# External object files for target Flex
Flex_EXTERNAL_OBJECTS =

Flex: CMakeFiles/Flex.dir/main.c.o
Flex: CMakeFiles/Flex.dir/build.make
Flex: CMakeFiles/Flex.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable Flex"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Flex.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Flex.dir/build: Flex

.PHONY : CMakeFiles/Flex.dir/build

CMakeFiles/Flex.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Flex.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Flex.dir/clean

CMakeFiles/Flex.dir/depend:
	cd /Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/adelinazagitova/CLionProjects/Flex /Users/adelinazagitova/CLionProjects/Flex /Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug /Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug /Users/adelinazagitova/CLionProjects/Flex/cmake-build-debug/CMakeFiles/Flex.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Flex.dir/depend

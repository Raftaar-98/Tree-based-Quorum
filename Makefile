JFLAGS = -cp
JC = javac
JVM = java
curr_dir = $(shell pwd)
default:
	$(JC)	$(curr_dir)/C1/Client1.java
	$(JC)	$(curr_dir)/C2/Client2.java
	$(JC)	$(curr_dir)/C3/Client3.java
	$(JC)	$(curr_dir)/C4/Client4.java
	$(JC)	$(curr_dir)/C5/Client5.java
	$(JC)	$(curr_dir)/S0/Server0.java
	$(JC)	$(curr_dir)/S1/Server1.java
	$(JC)	$(curr_dir)/S2/Server2.java
	$(JC)	$(curr_dir)/S3/Server3.java
	$(JC)	$(curr_dir)/S4/Server4.java
	$(JC)	$(curr_dir)/S5/Server5.java
	$(JC)	$(curr_dir)/S6/Server6.java
	$(JC)	$(curr_dir)/S7/Server7.java
clean:
	$(RM)	$(curr_dir)/C1/*.class
	$(RM)	$(curr_dir)/C2/*.class
	$(RM)	$(curr_dir)/C3/*.class
	$(RM)	$(curr_dir)/C4/*.class
	$(RM)	$(curr_dir)/C5/*.class
	$(RM)	$(curr_dir)/S0/*.class
	$(RM)	$(curr_dir)/S1/*.class
	$(RM)	$(curr_dir)/S2/*.class
	$(RM)	$(curr_dir)/S3/*.class
	$(RM)	$(curr_dir)/S4/*.class
	$(RM)	$(curr_dir)/S5/*.class
	$(RM)	$(curr_dir)/S6/*.class
	$(RM)	$(curr_dir)/S7/*.class
runC1:
	$(shell cd ..)
	$(JVM)	$(JFLAGS)	$(curr_dir) C1.C1
runC2:
	$(shell cd ..)
	$(shell cd C2)
	$(JVM)	$(JFLAGS)	$(curr_dir) C2.C2
runC3:
	$(shell cd ..)
	$(shell cd C3)
	$(JVM)	$(JFLAGS)	$(curr_dir) C3.C3

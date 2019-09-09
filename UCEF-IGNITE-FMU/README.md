# FMU-IGNITE
This is an FMU that implements a UDP client, enabling external simulators to send custom Drive Cycles to IGNITE's Cycle Driver.
## UDP server source code : 
* UDP-sockets/server.c
## Building UDP Server 

* gcc server.c -o server -lws2_32

## running the UDP Server : 

UDP-sockets>server.exe

## FMU source code : 

* fmusdk/fmu10/src/models/drivecycle

## Building the FMU on Windows

Execute the following command from *fmusdk/fmu1/src/models*:

* build_fmu cs drivecycle -win64

**Visual Studio 2019** is not supported.

**Visual Studio 2017** must be version 15.6 or newer with the *VC++ 2015.3 v140 toolset for desktop* individual component installed.

## simulating the FMU with fmusim:

fmusdk>fmusim cs10 fmu10\fmu\cs\x64\drivecycle.fmu 1500 1 0 s

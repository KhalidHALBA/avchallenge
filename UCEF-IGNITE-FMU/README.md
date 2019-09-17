# FMU-IGNITE

* Role: 

This is an FMU that implements a UDP client, enabling UCEF to send custom Drive Cycles to IGNITE's Cycle Driver.

# Building the FMU

C:\Users\USER\avchallenge\UCEF-IGNITE-FMU\fmusdk\fmu10\src\models>build_fmu cs drivecycle -win64

# Portability instructions


## ADS_DEMO (UCEF MACHINE)

* copy VM to your machine
* make sure it has 2 network interfaces : a NAT interface for internet connectivity and a Host-ONLY interface for communication with IGNITE  (For Mac OS you must add the host only interface manually).
* using if config on ADS_demo and ipconfig on WINDOWS2012VM_default make sure the "IGNITE_IP" parameter corresponds to IGNITE's VM.

## WINDOWS2012VM_default (IGNITE MACHINE)

* copy VM to your machine
* make sure UDP ports are enabled. (disabled by default)

## How to run

* Once Portability instructions above are respected : 
- Run IGNITE's solver first, when the Solver is up and running then run UCEF.  

# Outline 
=================================================================================
## A) NOTIONAL ARCHITECTURE
=================================================================================
# Outline 
=================================================================================
## A) VMs Preparation
## A.1- VMs Location
## A.2- VMs Configuration
## B) IGNITE installation guide : 
###	B.1- Download : 
###	B.2- Installation :
### B.3- License configuration :
## C) Configuring and Compiling the Experiment :
### C.1- UCEF Side :
### C.2- IGNITE Side : 
## D) Running the Experiment :
### D.1- IGNITE Side :
### D.2- UCEF Side :
=================================================================================
# A) VMs Preparation :
## A.1- VMs Location : 

Open Finder in MacOS, and use the "Connect to a Server tool"

![Image](https://imgur.com/C5XfcUv.png)

Connect to EL's shared drive using the link below : 

- smb://elwood.nist.gov/730/internal/

![Image](https://imgur.com/ERdaamA.png)

- Copy these two virtual machines to the desktop: 

![Image](https://imgur.com/NT3FZRr.png)

Ubuntu machine

- smb://elwood.nist.gov/730/internal/SGCPS/VirtualMachines/UCEF 101.ova
 
Windows machine :
 
- smb://elwood.nist.gov/730/internal/SGCPS/VirtualMachines/Windows2012VM_AV.ova

## A.2- VMs Configuration : 

### A.2.a- Install VirtualBox (at the time of this description 6.1.4 is the most recent version)

installation link : 

https://download.virtualbox.org/virtualbox/6.1.4/VirtualBox-6.1.4-136177-OSX.dmg


### A.2.b VMs Initial configuration

Once the VMs are copied to the Desktop, and virtualbox is installed, run the .ova files to configure CPU and Ram and click "Import" to load the VMs to VirtualBox menu for advanced configuration : 

- Windows Machine initial Configuration [Rename to IGNITE]

![Image](https://imgur.com/BP4FPim.png)

- Ubuntu Machine  initial Configuration [Rename to UCEF]

![Image](https://imgur.com/oyOqvrP.png)

- After Renaming, configuring ram and cpu, and importing the VMs, we get the outcome below : 

![Image](https://imgur.com/CYio2A0.png)

### A.2.c VMs Advanced configuration [both Virtual machines]

#### A.2.c.i- Minimum Configuration Settings

* CPU : 2 cores
* RAM : 4 GB
* Graphics : 128 mb & enable 3D acceleration


#### A.2.c.ii- Networking


* creating a host only interface : 


- Open the "Host Network Manager"

![Image](https://imgur.com/AstaDqi.png)

- Click Create and the host only interface "vboxnet0" will be created and the subnet 192.168.56.0/24 can now be used to ensure communication between both VMs.

![Image](https://imgur.com/keDietI.png)

* Add Host Only Interface to each VM

- UCEF

![Image](https://imgur.com/FqTfRVC.png)
![Image](https://imgur.com/OSSoCps.png)

- Windows 

![Image](https://imgur.com/BJriLEh.png)
![Image](https://imgur.com/4W07X4N.png)

#### A.2.c.iii- Add shared folder to the windows VM in order to copy the license file from the host machine.

* Shared Folder : Create a shared folder to the host machine Desktop folder in order to copy the license file to the windows machine.


![Image](https://imgur.com/BJriLEh.png)
![Image](https://imgur.com/kGlPhKt.png)

#### A.2.c.iv- Install Guest Additions

- UCEF

![Image](https://imgur.com/8eVacBk.png)
![Image](https://imgur.com/NYiE0WC.png)

- IGNITE

![Image](https://imgur.com/ZYxBqRy.png)
![Image](https://imgur.com/V5nQIez.png)

#### A.2.c.v- Enable Shared clipboard in order to copy/paste commands from host to VM.

- UCEF

![Image](https://imgur.com/0U46Wwo.png)

- IGNITE

![Image](https://imgur.com/ZtHayxg.png)

#### A.2.c.vi- Install Chrome on both VMs.

# B) IGNITE installation guide : 
##	B.1- Download : 
### B.1.a- in Internet Explorer : Goto https://software.ricardo.com/login and login to the website 
![Image](https://i.ibb.co/xj4bVzp/1.png)
### Go to release downloads using the link below : 
https://software.ricardo.com/support/release-downloads
### B.1.b- pick legacy releases.
![Image](https://imgur.com/mePPzE3.png)
### B.1.c- Pick 2018.1 release 
![Image](https://imgur.com/sTgZBtx.png)

##	B.2- Installation 

### B.2.a- Click on the installer

![Image](https://imgur.com/kaYtmja.png)

### B.2.b- Insert login and password and click next, accept agreement.

![Image](https://imgur.com/1lzj2Gn.png)

### B.2.c- Don’t forget to select the license manager when installing.

![Image](https://imgur.com/RKYs2hy.png)

##	B.3- License Configuration : 

Once the insallation is done :

### B.3.a- Copy the license file from the host machine to the virtual machine through the shared folder.

![Image](https://imgur.com/rzu7exq.png)

### B.3.b- Rename the license file provided by ricardo as : ricardo.lic and Move it to the folder below (create it if it doesn't exist): 

C:\Program Files\Ricardo\licmgr\licenses\ricardo.lic 


### B.3.c- Make sure the configuration the "License Manager Control Panel" is as correct : 

- open start menu and launch R-Desk (if it's not in the start menu use the search tool)

![Image](https://imgur.com/FiG55he.png)

- In the "Tools" menu to the right , click "Licensing Setup"

![Image](https://imgur.com/AAi1htV.png)

- License Manager Control Panel will pop up :

![Image](https://imgur.com/KPmMTjs.png)

- Unchek "License File" box if it's checked.
- Check "License Server(s)" box
- Pick "Single" in the Server Configuration radio buttons.
- On Server 1 : Insert the Windows Machine name that you can find by going to : " Control Panel > System and Security > System " as seen in the figure below : 
![Image](https://imgur.com/28OvK2y.png)
- On Server Search Path insert the machine name preceded by the "@" symbol as seen in the License Manager Control Panel figure above.

### B.3.d- Make sure the License service is up and running : 

- Open services from the start menu using the search tool

![Image](https://imgur.com/GX3gyr0.png)

- If it's not running right click on the service and click start

![Image](https://imgur.com/Tk0FR3b.png)

### B.3.e- Stop/Start the license service from LMTOOLS utility to make sure the license manager is up and running (Must Show "Server Start Successful" when started)

![Image](https://imgur.com/WNMaXvx.png)

### B.3.f- Disable Windows Firewall to allow UDP communications in and out of the IGNITE Machine

### B.3.f.i- Click Start and search for "Windows Firewall" and click on the icon below :

![Image](https://imgur.com/6qvTHTI.png)

### B.3.f.ii- Click on "Turn On/Off windows Firewall"

![Image](https://imgur.com/7k3Rmr6.png)

### B.3.f.iii- Make sure the Firewall is OFF as shown in the figure below : 

![Image](https://imgur.com/Fzxl2gX.png)

# C) Configuring and Compiling the Experiment :

## C.1- UCEF Side

* clone the avchallenge repository in /home/vagrant. 

`git clone https://github.com/usnistgov/avchallenge.git -b feature/latest`

### C.1.a- Gateway Federate UDP socket Configuration 

* IGNITE and UCEF communicate over a UDP socket, each end sends and receives data. Make sure both ends are on the same subnet. The UDP socket is configured in a way that we need to tell UCEF the IP address of the target system that hosts IGNITE. 

* This IP can be easily recognized by running the command ipconfig on the IGNITE machine, it's an address that falls in the host only subnet we have created "192.168.56.0/24"

![Image](https://imgur.com/TTiwQzS.png)

We use the UCEF Gateway Federate configuration file to set the target IP Address parameter, this configuration needs to be set in two locations  : 

- location 1) : /home/vagrant/avchallenge/ADS_generated/UCEFGateway/conf/UCEFGateway.json
- location 2) : /home/vagrant/avchallenge/ADS_deployment/conf/default/ucefgateway.json
- parameter to set : IGNITE_IP

![Image](https://i.ibb.co/F7HgD6T/10.png)


### C.1.b- Sampler & Lookahead Configuration

#### C.1.b.i- Sampler Configuration

* The sampler samples the FTP 75 drive cycle in a way that matches IGNITE's output time step. For a 10 HZ sampling rate the configuration is set in vehicle control federate as follows : 


- location 1) : /home/vagrant/avchallenge/ADS_generated/VehicleControl/conf/VehicleControl.json
- location 2) : /home/vagrant/avchallenge/ADS_deployment/conf/default/vehiclecontrol.json
- parameter to set : sampling_rate

![Image](https://i.ibb.co/BKBVsj7/11.png)


#### C.1.b.ii Lookahead Configuration

* The lookahead parameter describes the ability of the autonomous vehicle controller to anticipate speed and braking operations. It matches IGNITE's lookahead parameter. To modify on UCEF we use the "IGNITE_LOOKAHEAD" parameter in the following files : 

- location 1) : /home/vagrant/avchallenge/ADS_generated/VehicleControl/conf/VehicleControl.json
- location 2) : /home/vagrant/avchallenge/ADS_deployment/conf/default/vehiclecontrol.json

![Image](https://i.ibb.co/VBBFRvy/12.png)

## C.2- IGNITE Side 

### C.2.a- FMU Preparation


* install Git Bash for windows 

` https://gitforwindows.org/ `

* clone the avchallenge repository in C:\Users\vagrant\Desktop\ using Git Bash

`git clone https://github.com/usnistgov/avchallenge.git -b feature/latest`

#### C.2.a.i- FMU Editing

A Text/Code Editor of choice can be leveraged to edit the FMU Code. Visual Studio Code is our pick for this task:  It recognises both XML and C programming languages.

![image](https://i.ibb.co/rGCkH22/Screen-Shot-2020-01-12-at-23-54-49-PM.png)

#### C.2.a.ii- FMU Configuration : Important parameters 

We have to configure the following parameters before compiling the FMU : 

*  Refresh Rate
- The Refresh Rate value needs to be set on the FMU as well. It makes the socket ready to receive values at time durations equal to the refresh rate configured on UCEF's sampler and IGNITE's output time step.


*  Communication Delay 
- Use we IGNITE time to steer UCEF's drivce cycle generation. The time is carried in a UDP socket with a certain delay that needs to be compensated in order to match the native Drivecycle pattern. The communication_delay parameter enables the compensation of that time. It is set to 0.3 seconds in this experiment (measured delay).

![Image](https://i.ibb.co/kKwSh46/13.png)

#### C.2.a.iii- FMU Compilation

Developper Command Prompt is required to compile the FMU.

![image](https://i.ibb.co/NCGHMX2/Screen-Shot-2020-01-12-at-23-26-13-PM.png)

It comes within the Microsoft Visual Studio installer. We have picked the 2015 Community Edition to achieve this requirement.

![image](https://i.ibb.co/gjt7r39/Screen-Shot-2020-01-12-at-23-26-21-PM.png)

When installing Visual Studio 2015, make sure the following packages are selected : 

![image](https://i.ibb.co/zrrkd76/Screen-Shot-2020-01-12-at-23-58-29-PM.png)

After installing Developper Command Prompt, open it and run this command :

`C:\Users\vagrant\Desktop\avchallenge\UCEF-IGNITE-FMU\fmusdk\fmu10\src\models>build_fmu cs drivecycle -win64`

Make sure you run the command in the folder shown in the command above.


### C.2.b- IGNITE Preparation

* Start IGNITE using the Start Menu Search tool as seen in the figure below :
![image](https://imgur.com/nmPdF2O.png)
* Load the SUV Electric Vehicle Model : `C:\Program Files\Ricardo\2018.1\Products\IGNITE\Examples\Powertrain_library\Fuel_economy\SUV_electric\suv_electric.ignx`


![Image](https://i.ibb.co/0mvbh09/14.png)

- Vehicle Model is loaded !

![Image](https://i.ibb.co/CtgLrw3/15.png)

### C.2.c- Load FMU 

The pictures below show numbered steps to follow for loading the FMU : 

>  C.2.c.i-. Click on Active Library

![Image](https://i.ibb.co/DfTswX8/16.jpg)

>  Change Import type to "Fmu", the FMU location is : /Users/vagrant/Desktop/avchallenge/UCEF-IGNITE-FMU/fmusdk/fmu10/fmu/cs/x64/drivecycle.fmu

![Image](https://i.ibb.co/4TVX9Rx/aaaa.jpg)

>  when the Import process reaches 100% click import

![Image](https://i.ibb.co/hL2vH6Y/aaa.jpg)

> Drag and Drop the Loaded FMU to the Model space and delete the native drivecycle modelica component. 

![Image](https://i.ibb.co/0qJhTjz/22.jpg)


### C.2.d- Modelica Breakout Module 

For IGNITE 2019.1 , we choose the DriveCycleBreakout built in module.

For IGNITE 2018.1 , We follow the same steps we have seen above to load the modelica Module except : 

> step 3 :  we choose "Library" instead of FMU
> step 5 :  we load TCPLib.mo instead of drivecycle.c : Location : /Users/vagrant/Desktop/avchallenge/UCEF-IGNITE-FMU/fmusdk/fmu10/fmu/cs/x64/TCPLib.mo
> step 10 : we drag and drop


![Image](https://i.ibb.co/kx7mT7j/modmod.jpg)

### C.2.e- Connecting Modules 

Make sure to correctly connect modules inputs and outputs according to the numbers on the figure below : 

![Image](https://imgur.com/58lEJvr.png)

### C.2.f- Configuring IGNITE 

We configure Simulation, FMU, and Vehicle Settings : 

#### C.2.f.1- Simulation Time and Output time step (refresh rate ) and Tolerance.

![Image](https://i.ibb.co/W5M7jSj/IMG-0448.jpg)

#### C.2.f.2- Set Lookahead Time value to 1
![Image](https://i.ibb.co/cJQZsLR/IMG-0453.jpg)

#### C.2.f.3- Setting the FMU type to Co-Simulation
![Image](https://i.ibb.co/fqMTnVf/IMG-0449.jpg)

#### C.2.f.4- Setting the vehicle Parameters

* to set the vehicle mass for example (and other vehicle paramaters) follow the instructions below : 
![Image](https://i.ibb.co/fkjmgfT/IMG-0452.jpg)

# D) Running the Experiment :

## D.1- IGNITE Side

### D.1.a-  Running IGNITE

After loading the modelica vehicle model and the FMU as well as the Modelica Breakout component and connecting/configuring all the elements properly we can run the experiment. 


* On the session pane, Right click on (OCT 1) :  Model->Solutions->OCT_1 and click Run : 

![Image](https://imgur.com/KlB9H66.png)

* Wait until the solution monitor pops up and remains in listening mode : 

![Image](https://i.ibb.co/DV2wGfQ/Screen-Shot-2019-11-14-at-23-13-12-PM.png)

## D.2- UCEF Side 

- Building the federation : 

* On ADS_deployment folder run this command : mvn clean package
* On ADS_generated folder  run this command : bash build-all.sh

-  Running the Federation : 

![Image](https://i.ibb.co/0YjBtWt/Screen-Shot-2019-11-14-at-23-26-25-PM.png)

The federates are up and running :  

![Image](https://i.ibb.co/R3dpdwm/image002.png)

The data analytics federate shows the signals we are interested in : speed request, braking demand, and vehicle velocity in real time.

![Image](https://i.ibb.co/qdrKJPf/datanalytics.png)


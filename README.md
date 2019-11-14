# A) IGNITE installation guide : 
##	1- Download : 
### 1.a- Goto https://software.ricardo.com/login and login to the website 
![Image](https://i.ibb.co/xj4bVzp/1.png)
### Goto the download portal : 
https://software.ricardo.com/support/release-downloads
### 1.b- Pick on 2018.1 release 
![Image](https://i.ibb.co/JvtQGNq/2.png)
### 1.c- Click on RS_2018.1_windows_installer.exe
![Image](https://i.ibb.co/MsGz8FC/3.png)
##	2- Installation 
### 2.a- Click on the installer
![Image](https://i.ibb.co/BfFP5cd/4.png)
### 2.b- Insert login and password and click next, accept agreement.
### 2.c- Don’t forget to select the license manager when installing 
![Image](https://i.ibb.co/NWpHkb3/5.png)
##	3- Configuration : once the insallation is done :
### 3.a- Rename the license file provided by ricardo as : ricardo.lic and Move it to : 
C:\Program Files\Ricardo\licmgr\licenses\ricardo.lic 
### 3.b- Make sure the configuration on Ricardo License Manager Control Panel is as follows 
![Image](https://i.ibb.co/qMs1Gtm/6.png)
### 3.c- Make sure the License service is up and running, if not , right click on the service highlighter and click start.
![Image](https://i.ibb.co/vBnrHGF/7.png)
### 3.d- You can also start/stop or check the status of the license service from LMTOOLS utility 
#### 3.d.i- start/stop the service 
![Image](https://i.ibb.co/1ntMgpV/8.png)
#### 3.d.ii- check status
![Image](https://i.ibb.co/qnrLvnD/9.png)
# B) Configuring the Experiment :

## 1) UCEF Side
* clone the avchallenge repository in /home/vagrant. 
`git clone https://github.com/usnistgov/avchallenge.git -b feature/latest`

### 1.a) UCEF VM Configuration  
#### 1.a.i) Minimum Configuration Settings
* CPU : 4 cores
* RAM : 4 GB
#### 1.a.ii) Networking
* Add NAT interface
* Add Host Only Interface
### 1.b) Gateway Federate UDP socket Configuration 

* IGNITE and UCEF communicate over a UDP socket, each end sends and receives data. Make sure both ends are on the same subnet. The UDP socket is configured in a way that we need to tell UCEF the target system that hosts IGNITE. We use the UCEF Gateway Federate configuration file to set the target IP Address parameter, this configuration needs to be set in two locations  : 

- location 1) : /home/vagrant/avchallenge/ADS_generated/UCEFGateway/conf/UCEFGateway.json
- location 2) : /home/vagrant/avchallenge/ADS_deployment/conf/default/ucefgateway.json
- parameter to set : IGNITE IP

![Image](https://i.ibb.co/F7HgD6T/10.png)


### 1.c) Sampler & Lookahead Configuration

### 1.c.i) Sampler Configuration

* The sampler samples the FTP 75 drive cycle in a way that matches IGNITE's output time step. For a 10 HZ sampling rate the configuration is set in vehicle control federate as follows : 


- location 1) : /home/vagrant/avchallenge/ADS_generated/VehicleControl/conf/VehicleControl.json
- location 2) : /home/vagrant/avchallenge/ADS_deployment/conf/default/vehiclecontrol.json
- parameter to set : sampling_rate

![Image](https://i.ibb.co/BKBVsj7/11.png)

* The lookahead parameter describes the ability of the autonomous vehicle controller to anticipate speed and braking operations. It matches IGNITE's lookahead parameter. To modify on UCEF we use the "IGNITE_LOOKAHEAD" parameter in the following files : 


- location 1) : /home/vagrant/avchallenge/ADS_generated/VehicleControl/conf/VehicleControl.json
- location 2) : /home/vagrant/avchallenge/ADS_deployment/conf/default/vehiclecontrol.json

![Image](https://i.ibb.co/VBBFRvy/12.png)

### 1.c.i) Lookahead Configuration


## 2) IGNITE Side 

### 2.a) IGNITE Configuration  

### 2.b) FMU UDP socket Configuration 


# C) Running the Experiment :


## 1) IGNITE Side

### 1.a)  Parameters Settings 
### 1.b)  Running IGNITE
### 1.c)  Data Viewing 

## 2) UCEF Side 

### 2.a)  Parameters Settings 
### 2.b)  Running UCEF
### 2.c)  Data Logging & Viewing 




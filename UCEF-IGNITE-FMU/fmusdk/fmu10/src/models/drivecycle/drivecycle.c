#define MODEL_IDENTIFIER drivecycle
#define MODEL_GUID "{8c4e810f-3df3-4a00-8276-176fa3c9f008}"
#define NUMBER_OF_REALS 5
#define NUMBER_OF_INTEGERS 1
#define NUMBER_OF_BOOLEANS 0
#define NUMBER_OF_STRINGS 0
#define NUMBER_OF_STATES 0
#define NUMBER_OF_EVENT_INDICATORS 0
#define BUFLEN 512	//Max length of buffer
#define PORT 8888
#include "fmuTemplate.h"
#include <winsock2.h>
#include <wS2tcpip.h>
#include <stdio.h> 
#include <windows.h>

#pragma comment( lib, "ws2_32.lib")
int xx = 0;

#include <stdlib.h> 
#include <string.h> 
#define cycle_speed_kmph_ 0
#define cycle_time_ahead_ 1
#define cycle_time_ 2
#define cycle_speed_ahead_kmph_ 3
#define vehicle_velocity_ 4
#define counter_ 5

void setStartValues(ModelInstance *comp) {
    r(cycle_speed_kmph_) = 0;
    r(cycle_time_) = 0;
    r(cycle_speed_ahead_kmph_) = 0;
    r(cycle_time_ahead_) = 0;
	r(vehicle_velocity_) = 0;
    i(counter_) = 0;
}
fmiReal getReal(ModelInstance* comp, fmiValueReference vr){
    switch (vr) {
        case cycle_speed_kmph_ : return r(cycle_speed_kmph_);
        case cycle_time_ : return r(cycle_time_);
        case cycle_speed_ahead_kmph_ : return r(cycle_speed_ahead_kmph_);
        case cycle_time_ahead_ : return r(cycle_time_ahead_);
		case vehicle_velocity_ : return r(vehicle_velocity_);
        default: return 0;
    }
}
void initialize(ModelInstance* comp, fmiEventInfo* eventInfo) {
    eventInfo->upcomingTimeEvent   = fmiTrue;
    eventInfo->nextEventTime       = 1 + comp->time;
}
void eventUpdate(ModelInstance* comp, fmiEventInfo* eventInfo) {


SOCKET s;
	struct sockaddr_in server, si_other;
	int slen , recv_len;
	char buf[BUFLEN];
	char bufr[BUFLEN];
	WSADATA wsa;

	slen = sizeof(si_other) ;
	
	//Initialise winsock
	// printf("\nInitialising Winsock...");
	if (WSAStartup(MAKEWORD(2,2),&wsa) != 0)
	{
		// printf("Failed. Error Code : %d",WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	// printf("Initialised.\n");
	
	//Create a socket
	if((s = socket(AF_INET , SOCK_DGRAM , 0 )) == INVALID_SOCKET)
	{
		// printf("Could not create socket : %d" , WSAGetLastError());
	}
	// printf("Socket created.\n");
	
	//Prepare the sockaddr_in structure
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons( PORT );



	//Bind
	if( bind(s ,(struct sockaddr *)&server , sizeof(server)) == SOCKET_ERROR)
	{
		// printf("Bind failed with error code : %d" , WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	puts("Bind done");




	//keep listening for data
    // try while(counter<value)


    i(counter_) += 1;

    if (i(counter_) > 2000) 
        eventInfo->terminateSimulation = fmiTrue;
    else {


        eventInfo->upcomingTimeEvent   = fmiTrue;
        eventInfo->nextEventTime       = 1 + comp->time;
    








	while(1)
	{

		fflush(stdout);
		

		memset(buf,0, BUFLEN);
		
		memset(bufr,0, BUFLEN);

		if ((recv_len = recvfrom(s, buf, BUFLEN, 0, (struct sockaddr *) &si_other, &slen)) == SOCKET_ERROR)
		{
			exit(EXIT_FAILURE);
		}
		
        r(cycle_speed_kmph_)=(float)( atoi(buf));
		// r(cycle_speed_ahead_kmph_)=(float)( atoi(buf) + (rand()%5) );

		 printf("DriveCycle %f VehicleResponse %f CycleTime %f\n" ,  r(cycle_speed_kmph_), (((r(vehicle_velocity_))/1000)*(3600)), r(cycle_time_));

double a=(((r(vehicle_velocity_))/1000)*(3600));

 double floating = a, fractional, integer;

    fractional = modf(floating, &integer);

int diff = 1000*(floating-integer);



    printf ("Floating: %g\nInteger: %g\nFractional: %d", floating, integer, diff); // when using printf, there are no floats


int numm = integer;

  int y = integer;
  char str1[10];

  sprintf(str1, "%d", y);

  int u = diff;
  char str2[10];

  sprintf(str2, "%d", u);

strcat(str1, ".");
strcat(str1, str2);



    sprintf(bufr, "%s",  str1 );


      

    printf("\n a %s", str1); 

		if (sendto(s, bufr, recv_len, 0, (struct sockaddr*) &si_other, slen) == SOCKET_ERROR)
		{
			exit(EXIT_FAILURE);
		}
	
    break;

//try without break

    }


}
	closesocket(s);
	WSACleanup();
	
	return;

 }
#include "fmuTemplate.c"
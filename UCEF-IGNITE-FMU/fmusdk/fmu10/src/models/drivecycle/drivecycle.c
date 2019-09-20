#define MODEL_IDENTIFIER drivecycle
#define MODEL_GUID "{8c4e810f-3df3-4a00-8276-176fa3c9f008}"
#define NUMBER_OF_REALS 6
#define NUMBER_OF_INTEGERS 1
#define NUMBER_OF_BOOLEANS 0
#define NUMBER_OF_STRINGS 0
#define NUMBER_OF_STATES 0
#define NUMBER_OF_EVENT_INDICATORS 0
#define BUFLEN 1024 //Max length of buffer
#define PORT 8888
#include "fmuTemplate.h"
#include <winsock2.h>
#include <stdint.h>
#include <wS2tcpip.h>
#include <stdio.h>
#include <windows.h>

#pragma comment(lib, "ws2_32.lib")

#include <stdlib.h>
#include <string.h>
#define cycle_speed_kmph_ 0
#define cycle_time_ahead_ 1
#define cycle_time_ 2
#define cycle_speed_ahead_kmph_ 3
#define vehicle_velocity_ 4
#define driver_brake_demand_ 5
#define counter_ 0

void setStartValues(ModelInstance *comp)
{
	r(cycle_speed_kmph_) = 0;
	r(cycle_time_) = 0;
	r(cycle_speed_ahead_kmph_) = 0;
	r(cycle_time_ahead_) = 0;
	r(vehicle_velocity_) = 0;
	r(driver_brake_demand_) = 0;
	i(counter_) = 0;
}
fmiReal getReal(ModelInstance *comp, fmiValueReference vr)
{
	switch (vr)
	{
	case cycle_speed_kmph_:
		return r(cycle_speed_kmph_);
	case cycle_time_:
		return r(cycle_time_);
	case cycle_speed_ahead_kmph_:
		return r(cycle_speed_ahead_kmph_);
	case cycle_time_ahead_:
		return r(cycle_time_ahead_);
	case vehicle_velocity_:
		return r(vehicle_velocity_);
	case driver_brake_demand_:
		return r(driver_brake_demand_);
	default:
		return 0;
	}
}
void initialize(ModelInstance *comp, fmiEventInfo *eventInfo)
{
	eventInfo->upcomingTimeEvent = fmiTrue;
	eventInfo->nextEventTime = 1 + comp->time;
}



void eventUpdate(ModelInstance *comp, fmiEventInfo *eventInfo)
{

	SOCKET s;
	struct sockaddr_in server, si_other;
	int slen, recv_len;
	char buf[BUFLEN];

	WSADATA wsa;

	slen = sizeof(si_other);

	//Initialise winsock
	// printf("\nInitialising Winsock...");
	if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0)
	{
		// printf("Failed. Error Code : %d",WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	// printf("Initialised.\n");

	//Create a socket
	if ((s = socket(AF_INET, SOCK_DGRAM, 0)) == INVALID_SOCKET)
	{
		// printf("Could not create socket : %d" , WSAGetLastError());
	}
	// printf("Socket created.\n");

	//Prepare the sockaddr_in structure
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(PORT);

	//Bind
	if (bind(s, (struct sockaddr *)&server, sizeof(server)) == SOCKET_ERROR)
	{
		// printf("Bind failed with error code : %d" , WSAGetLastError());
		exit(EXIT_FAILURE);
	}
	// puts("Bind done");
	i(counter_) += 1;

		eventInfo->upcomingTimeEvent = fmiTrue;
		eventInfo->nextEventTime = 0.1 + comp->time;

		fflush(stdout);
		memset(buf, 0, BUFLEN);

		if ((recv_len = recvfrom(s, buf, BUFLEN, 0, (struct sockaddr *)&si_other, &slen)) == SOCKET_ERROR)
		{
			exit(EXIT_FAILURE);
		}

		r(cycle_speed_kmph_) = (float)(atoi(buf));

		
		int a = 100 * ((r(vehicle_velocity_)) * 3.6);
		int b = 10000 * r(driver_brake_demand_);
		int t = 100*(r(cycle_time_));


		// extract speed decimal values

		int aa1 = a / 1000;
		int aa2 = ((a - (aa1 * 1000)) / 100);
		int aa3 = ((a - (aa1 * 1000) - (aa2 * 100))/10);
		int aa4 = a - (aa1 * 1000) - (aa2 * 100) - (aa3*10);

		// extract braking decimal values

		int bb1 = b / 1000;
		int bb2 = ((b - (bb1 * 1000)) / 100);
		int bb3 = ((b - (bb1 * 1000) - (bb2 * 100))/10);
		int bb4 = b - (bb1 * 1000) - (bb2 * 100) - (bb3*10);


		// extract CycleTime decimal values

		int tt1 = t / 10000;
		int tt2 = ((t - (tt1 * 10000)) / 1000);
		int tt3 = ((t - (tt1 * 10000) - (tt2 * 1000))/100);
		int tt4 = ((t - (tt1 * 10000) - (tt2 * 1000) - (tt3*100))/10);
		int tt5 = t - (tt1 * 10000) - (tt2 * 1000) - (tt3*100) - (tt4*10) ;

		// convert to ascii

		char s1 = '0' + aa1;
		char s2 = '0' + aa2;
		char s3 = '0' + aa3;
        char s4 = '0' + aa4;
        

		char b1 = '0' + bb1;
		char b2 = '0' + bb2;
		char b3 = '0' + bb3;
		char b4 = '0' + bb4;


		char t1 = '0' + tt1;
		char t2 = '0' + tt2;
		char t3 = '0' + tt3;
		char t4 = '0' + tt4;
		char t5 = '0' + tt5;

        printf(" update a %d                         b %d                        t %d \n", a, b, t);
		printf(" update aa1 %d  aa2 %d aa3 %d aa4 %d bb1 %d bb2 %d bb3 %d bb4 %d tt1 %d tt2 %d tt3 %d tt4 %d tt5 %d \n", aa1, aa2, aa3, aa4, bb1, bb2, bb3, bb4, tt1, tt2, tt3, tt4, tt5);

		char DYNAMICS[] = {s1, s2, s3, s4, (char)32, b1, b2, b3, b4, (char)32, t1, t2, t3, t4, t5};

		if (sendto(s, DYNAMICS, sizeof(DYNAMICS), 0, (struct sockaddr *)&si_other, slen) == SOCKET_ERROR)
		{
			exit(EXIT_FAILURE);
		}

	closesocket(s);
	WSACleanup();

	return;
}
#include "fmuTemplate.c"
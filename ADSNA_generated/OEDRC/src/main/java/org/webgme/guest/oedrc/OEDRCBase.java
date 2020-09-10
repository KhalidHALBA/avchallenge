package org.webgme.guest.oedrc;

import org.webgme.guest.oedrc.rti.*;

import hla.rti.EventRetractionHandle;
import hla.rti.LogicalTime;
import hla.rti.ReceivedInteraction;

import org.cpswt.hla.C2WInteractionRoot;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.SubscribedInteractionFilter;
import org.cpswt.hla.SynchronizedFederate;

import org.cpswt.config.FederateConfig;
import org.cpswt.utils.CpswtDefaults;

import org.cpswt.*;


public class OEDRCBase extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public OEDRCBase(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub

        // object pubsub
        ScenarioDeviationData.publish_Protocol();
        ScenarioDeviationData.publish_Value();
        ScenarioDeviationData.publish(getLRC());
        EnvironmentScenario.publish_Protocol();
        EnvironmentScenario.publish_obstacle_presence();
        EnvironmentScenario.publish_obstacle_presence_ahead();
        EnvironmentScenario.publish(getLRC());
        TripPlanMessage.subscribe_DriveCycle();
        TripPlanMessage.subscribe_Protocol();
        TripPlanMessage.subscribe(getLRC());
        VehiclePhysicsState.subscribe_Break();
        VehiclePhysicsState.subscribe_PhysicsTime();
        VehiclePhysicsState.subscribe_Protocol();
        VehiclePhysicsState.subscribe_otherPhysicsSignals();
        VehiclePhysicsState.subscribe_vehicleSpeed();
        VehiclePhysicsState.subscribe_vehicleSpeedAhead();
        VehiclePhysicsState.subscribe(getLRC());
        Injections.subscribe_Protocol();
        Injections.subscribe_Value();
        Injections.subscribe(getLRC());
        SensorData.subscribe_Protocol();
        SensorData.subscribe_Value();
        SensorData.subscribe(getLRC());
        RealTimeCommsData.subscribe_Protocol();
        RealTimeCommsData.subscribe_Value();
        RealTimeCommsData.subscribe(getLRC());
    }


    @Override
    public void receiveInteraction(int interactionClass,
                                   ReceivedInteraction theInteraction,
                                   byte[] userSuppliedTag) {
        InteractionRoot interactionRoot =
            InteractionRoot.create_interaction(interactionClass,
                                               theInteraction);
        if (interactionRoot instanceof C2WInteractionRoot) {
            C2WInteractionRoot c2wInteractionRoot =
                (C2WInteractionRoot)interactionRoot;

            // Filter interaction if src/origin fed requirements (if any)
            // are not met
            if (_subscribedInteractionFilter.filterC2WInteraction
                (getFederateId(), c2wInteractionRoot)) {
                return;
            }
        }
        super.receiveInteraction(interactionClass, theInteraction,
                                 userSuppliedTag);
    }

    @Override
    public void receiveInteraction(int interactionClass,
                                   ReceivedInteraction theInteraction,
                                   byte[] userSuppliedTag,
                                   LogicalTime theTime,
                                   EventRetractionHandle retractionHandle) {
        InteractionRoot interactionRoot =
            InteractionRoot.create_interaction(interactionClass,
                                               theInteraction, theTime);
        if (interactionRoot instanceof C2WInteractionRoot) {
            C2WInteractionRoot c2wInteractionRoot =
                (C2WInteractionRoot)interactionRoot;

            // Filter interaction if src/origin fed requirements (if any)
            // are not met
            if (_subscribedInteractionFilter.filterC2WInteraction
                (getFederateId(), c2wInteractionRoot)) {
                return;
            }
        }
        super.receiveInteraction(interactionClass, theInteraction,
                                 userSuppliedTag, theTime, retractionHandle);
    }
}

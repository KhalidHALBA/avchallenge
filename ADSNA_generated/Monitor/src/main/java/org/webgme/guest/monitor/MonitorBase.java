package org.webgme.guest.monitor;

import org.webgme.guest.monitor.rti.*;

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


public class MonitorBase extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public MonitorBase(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub

        // object pubsub
        OESNRevision.publish_Protocol();
        OESNRevision.publish_Value();
        OESNRevision.publish(getLRC());
        OESAResvision.publish_Protocol();
        OESAResvision.publish_Value();
        OESAResvision.publish(getLRC());
        ScenarioFeedback.publish_Protocol();
        ScenarioFeedback.publish_Value();
        ScenarioFeedback.publish(getLRC());
        FailSafeOpSignal.publish_Protocol();
        FailSafeOpSignal.publish_Value();
        FailSafeOpSignal.publish(getLRC());
        DDTPerfDeviationData.publish_Protocol();
        DDTPerfDeviationData.publish_Value();
        DDTPerfDeviationData.publish(getLRC());
        EnvironmentScenario.subscribe_Protocol();
        EnvironmentScenario.subscribe_obstacle_presence();
        EnvironmentScenario.subscribe_obstacle_presence_ahead();
        EnvironmentScenario.subscribe(getLRC());
        SensorData.subscribe_Protocol();
        SensorData.subscribe_Value();
        SensorData.subscribe(getLRC());
        SensorStatus.subscribe_Protocol();
        SensorStatus.subscribe_Value();
        SensorStatus.subscribe(getLRC());
        VehiclePhysicsState.subscribe_Break();
        VehiclePhysicsState.subscribe_PhysicsTime();
        VehiclePhysicsState.subscribe_Protocol();
        VehiclePhysicsState.subscribe_otherPhysicsSignals();
        VehiclePhysicsState.subscribe_vehicleSpeed();
        VehiclePhysicsState.subscribe_vehicleSpeedAhead();
        VehiclePhysicsState.subscribe(getLRC());
        RealTimeCommsData.subscribe_Protocol();
        RealTimeCommsData.subscribe_Value();
        RealTimeCommsData.subscribe(getLRC());
        CommsStatus.subscribe_Protocol();
        CommsStatus.subscribe_Value();
        CommsStatus.subscribe(getLRC());
        LocationTime.subscribe_Protocol();
        LocationTime.subscribe_Value();
        LocationTime.subscribe(getLRC());
        TripPlanMessage.subscribe_DriveCycle();
        TripPlanMessage.subscribe_Protocol();
        TripPlanMessage.subscribe(getLRC());
        TorqueRequestsOv.subscribe_Protocol();
        TorqueRequestsOv.subscribe_Value();
        TorqueRequestsOv.subscribe(getLRC());
        TorqueRequestsOp.subscribe_Attribute();
        TorqueRequestsOp.subscribe_Protocol();
        TorqueRequestsOp.subscribe_speed_request();
        TorqueRequestsOp.subscribe_speed_request_ahead();
        TorqueRequestsOp.subscribe(getLRC());
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

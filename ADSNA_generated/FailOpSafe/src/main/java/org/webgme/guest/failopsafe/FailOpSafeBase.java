package org.webgme.guest.failopsafe;

import org.webgme.guest.failopsafe.rti.*;

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


public class FailOpSafeBase extends SynchronizedFederate {
    private SubscribedInteractionFilter _subscribedInteractionFilter =
        new SubscribedInteractionFilter();

    // constructor
    public FailOpSafeBase(FederateConfig config) throws Exception {
        super(config);
        super.createLRC();
        super.joinFederation();
        enableTimeConstrained();
        enableTimeRegulation(getLookAhead());
        enableAsynchronousDelivery();

        // interaction pubsub

        // object pubsub
        TorqueRequestsOv.publish_Protocol();
        TorqueRequestsOv.publish_Value();
        TorqueRequestsOv.publish(getLRC());
        LocationTime.subscribe_Protocol();
        LocationTime.subscribe_Value();
        LocationTime.subscribe(getLRC());
        TripPlanMessage.subscribe_DriveCycle();
        TripPlanMessage.subscribe_Protocol();
        TripPlanMessage.subscribe(getLRC());
        OESActualMessage.subscribe_Protocol();
        OESActualMessage.subscribe_Value();
        OESActualMessage.subscribe(getLRC());
        OESNominalMessage.subscribe_Protocol();
        OESNominalMessage.subscribe_Value();
        OESNominalMessage.subscribe(getLRC());
        FailSafeOpSignal.subscribe_Protocol();
        FailSafeOpSignal.subscribe_Value();
        FailSafeOpSignal.subscribe(getLRC());
        EnvironmentScenario.subscribe_Protocol();
        EnvironmentScenario.subscribe_obstacle_presence();
        EnvironmentScenario.subscribe_obstacle_presence_ahead();
        EnvironmentScenario.subscribe(getLRC());
        DDTPerfDeviationData.subscribe_Protocol();
        DDTPerfDeviationData.subscribe_Value();
        DDTPerfDeviationData.subscribe(getLRC());
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

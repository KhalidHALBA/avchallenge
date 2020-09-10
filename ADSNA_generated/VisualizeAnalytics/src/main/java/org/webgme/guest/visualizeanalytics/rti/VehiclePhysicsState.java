package org.webgme.guest.visualizeanalytics.rti;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;

import hla.rti.AttributeHandleSet;
import hla.rti.FederateNotExecutionMember;
import hla.rti.LogicalTime;
import hla.rti.NameNotFound;
import hla.rti.ObjectClassNotDefined;
import hla.rti.ObjectClassNotPublished;
import hla.rti.ObjectClassNotSubscribed;
import hla.rti.RTIambassador;
import hla.rti.ReflectedAttributes;
import hla.rti.SuppliedAttributes;

import org.cpswt.hla.*;

/**
* Implements ObjectRoot.VehiclePhysicsState
*/
public class VehiclePhysicsState extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the VehiclePhysicsState object class with default attribute values.
    */
    public VehiclePhysicsState() {}

    private static int _Break_handle;
    private static int _PhysicsTime_handle;
    private static int _Protocol_handle;
    private static int _otherPhysicsSignals_handle;
    private static int _vehicleSpeed_handle;
    private static int _vehicleSpeedAhead_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the VehiclePhysicsState object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the handle of the class pertaining to the reference,
    * rather than the handle of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassHandle()}.
    *
    * @return the RTI assigned integer handle that represents this object class
    */
    public static int get_handle() {
        return _handle;
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of the VehiclePhysicsState object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.VehiclePhysicsState";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the VehiclePhysicsState object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "VehiclePhysicsState";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * VehiclePhysicsState object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getAttributeNames()}.
    *
    * @return a modifiable set of the non-hidden attribute names for this object class
    */
    public static Set< String > get_attribute_names() {
        return new HashSet< String >(_datamemberNames);
    }

    /**
    * Returns a set containing the names of all of the attributes in the
    * VehiclePhysicsState object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getAttributeNames()}.
    *
    * @return a modifiable set of the attribute names for this object class
    */
    public static Set< String > get_all_attribute_names() {
        return new HashSet< String >(_allDatamemberNames);
    }

    private static Set< String > _publishAttributeNameSet = new HashSet< String >();
    private static Set< String > _subscribeAttributeNameSet = new HashSet< String >();

    static {
        _classNameSet.add("ObjectRoot.VehiclePhysicsState");
        _classNameClassMap.put("ObjectRoot.VehiclePhysicsState", VehiclePhysicsState.class);

        _datamemberClassNameSetMap.put("ObjectRoot.VehiclePhysicsState", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.VehiclePhysicsState", _allDatamemberNames);

        _datamemberNames.add("Break");
        _datamemberNames.add("PhysicsTime");
        _datamemberNames.add("Protocol");
        _datamemberNames.add("otherPhysicsSignals");
        _datamemberNames.add("vehicleSpeed");
        _datamemberNames.add("vehicleSpeedAhead");

        _datamemberTypeMap.put("Break", "String");
        _datamemberTypeMap.put("PhysicsTime", "String");
        _datamemberTypeMap.put("Protocol", "String");
        _datamemberTypeMap.put("otherPhysicsSignals", "String");
        _datamemberTypeMap.put("vehicleSpeed", "String");
        _datamemberTypeMap.put("vehicleSpeedAhead", "String");

        _allDatamemberNames.add("Break");
        _allDatamemberNames.add("PhysicsTime");
        _allDatamemberNames.add("Protocol");
        _allDatamemberNames.add("otherPhysicsSignals");
        _allDatamemberNames.add("vehicleSpeed");
        _allDatamemberNames.add("vehicleSpeedAhead");

        _classNamePublishAttributeNameMap.put("ObjectRoot.VehiclePhysicsState", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.VehiclePhysicsState", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.VehiclePhysicsState");
                isNotInitialized = false;
            } catch (FederateNotExecutionMember e) {
                logger.error("could not initialize: Federate Not Execution Member", e);
                return;
            } catch (NameNotFound e) {
                logger.error("could not initialize: Name Not Found", e);
                return;
            } catch (Exception e) {
                logger.error(e);
                CpswtUtils.sleepDefault();
            }
        }

        _classNameHandleMap.put("ObjectRoot.VehiclePhysicsState", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.VehiclePhysicsState");
        _classHandleSimpleNameMap.put(get_handle(), "VehiclePhysicsState");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _Break_handle = rti.getAttributeHandle("Break", get_handle());
                _PhysicsTime_handle = rti.getAttributeHandle("PhysicsTime", get_handle());
                _Protocol_handle = rti.getAttributeHandle("Protocol", get_handle());
                _otherPhysicsSignals_handle = rti.getAttributeHandle("otherPhysicsSignals", get_handle());
                _vehicleSpeed_handle = rti.getAttributeHandle("vehicleSpeed", get_handle());
                _vehicleSpeedAhead_handle = rti.getAttributeHandle("vehicleSpeedAhead", get_handle());
                isNotInitialized = false;
            } catch (FederateNotExecutionMember e) {
                logger.error("could not initialize: Federate Not Execution Member", e);
                return;
            } catch (ObjectClassNotDefined e) {
                logger.error("could not initialize: Object Class Not Defined", e);
                return;
            } catch (NameNotFound e) {
                logger.error("could not initialize: Name Not Found", e);
                return;
            } catch (Exception e) {
                logger.error(e);
                CpswtUtils.sleepDefault();
            }
        }

        _datamemberNameHandleMap.put("ObjectRoot.VehiclePhysicsState.Break", _Break_handle);
        _datamemberNameHandleMap.put("ObjectRoot.VehiclePhysicsState.PhysicsTime", _PhysicsTime_handle);
        _datamemberNameHandleMap.put("ObjectRoot.VehiclePhysicsState.Protocol", _Protocol_handle);
        _datamemberNameHandleMap.put("ObjectRoot.VehiclePhysicsState.otherPhysicsSignals", _otherPhysicsSignals_handle);
        _datamemberNameHandleMap.put("ObjectRoot.VehiclePhysicsState.vehicleSpeed", _vehicleSpeed_handle);
        _datamemberNameHandleMap.put("ObjectRoot.VehiclePhysicsState.vehicleSpeedAhead", _vehicleSpeedAhead_handle);

        _datamemberHandleNameMap.put(_Break_handle, "Break");
        _datamemberHandleNameMap.put(_PhysicsTime_handle, "PhysicsTime");
        _datamemberHandleNameMap.put(_Protocol_handle, "Protocol");
        _datamemberHandleNameMap.put(_otherPhysicsSignals_handle, "otherPhysicsSignals");
        _datamemberHandleNameMap.put(_vehicleSpeed_handle, "vehicleSpeed");
        _datamemberHandleNameMap.put(_vehicleSpeedAhead_handle, "vehicleSpeedAhead");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the VehiclePhysicsState object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.VehiclePhysicsState." + attributeName));
                logger.trace("publish {}:{}", get_class_name(), attributeName);
            } catch (Exception e) {
                logger.error("could not publish \"" + attributeName + "\" attribute.", e);
            }
        }

        synchronized(rti) {
            boolean isNotPublished = true;
            while(isNotPublished) {
                try {
                    rti.publishObjectClass(get_handle(), publishedAttributeHandleSet);
                    isNotPublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not publish: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not publish: Object Class Not Defined", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isPublished = true;
        logger.debug("publish: {}", get_class_name());
    }

    /**
    * Unpublishes the VehiclePhysicsState object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void unpublish(RTIambassador rti) {
        if (!_isPublished) return;

        init(rti);

        synchronized(rti) {
            boolean isNotUnpublished = true;
            while(isNotUnpublished) {
                try {
                    rti.unpublishObjectClass(get_handle());
                    isNotUnpublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unpublish: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not unpublish: Object Class Not Defined", e);
                    return;
                } catch (ObjectClassNotPublished e) {
                    logger.error("could not unpublish: Object Class Not Published", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isPublished = false;
        logger.debug("unpublish: {}", get_class_name());
    }

    private static boolean _isSubscribed = false;

    /**
    * Subscribes a federate to the VehiclePhysicsState object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.VehiclePhysicsState." + attributeName));
                logger.trace("subscribe {}:{}", get_class_name(), attributeName);
            } catch (Exception e) {
                logger.error("could not subscribe to \"" + attributeName + "\" attribute.", e);
            }
        }

        synchronized(rti) {
            boolean isNotSubscribed = true;
            while(isNotSubscribed) {
                try {
                    rti.subscribeObjectClassAttributes(get_handle(), subscribedAttributeHandleSet);
                    isNotSubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not subscribe: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not subscribe: Object Class Not Defined", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isSubscribed = true;
        logger.debug("subscribe: {}", get_class_name());
    }

    /**
    * Unsubscribes a federate from the VehiclePhysicsState object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void unsubscribe(RTIambassador rti) {
        if (!_isSubscribed) return;

        init(rti);

        synchronized(rti) {
            boolean isNotUnsubscribed = true;
            while(isNotUnsubscribed) {
                try {
                    rti.unsubscribeObjectClass(get_handle());
                    isNotUnsubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unsubscribe: Federate Not Execution Member", e);
                    return;
                } catch (ObjectClassNotDefined e) {
                    logger.error("could not unsubscribe: Object Class Not Defined", e);
                    return;
                } catch (ObjectClassNotSubscribed e) {
                    logger.error("could not unsubscribe: Object Class Not Subscribed", e);
                    return;
                } catch (Exception e) {
                    logger.error(e);
                    CpswtUtils.sleepDefault();
                }
            }
        }

        _isSubscribed = false;
        logger.debug("unsubscribe: {}", get_class_name());
    }

    /**
    * Return true if "handle" is equal to the handle (RTI assigned) of this class
    * (that is, the VehiclePhysicsState object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the VehiclePhysicsState object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the VehiclePhysicsState object class).
    */
    public static boolean match(int handle) {
        return handle == get_handle();
    }

    /**
    * Returns the handle (RTI assigned) of this instance's object class .
    *
    * @return the handle (RTI assigned) if this instance's object class
    */
    public int getClassHandle() {
        return get_handle();
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of this instance's object class.
    *
    * @return the fully-qualified (dot-delimited) name of this instance's object class
    */
    public String getClassName() {
        return get_class_name();
    }

    /**
    * Returns the simple name (last name in its fully-qualified dot-delimited name)
    * of this instance's object class.
    *
    * @return the simple name of this instance's object class
    */
    public String getSimpleClassName() {
        return get_simple_class_name();
    }

    /**
    * Returns a set containing the names of all of the non-hiddenattributes of an
    * object class instance.
    *
    * @return set containing the names of all of the attributes of an
    * object class instance
    */
    public Set< String > getAttributeNames() {
        return get_attribute_names();
    }

    /**
    * Returns a set containing the names of all of the attributes of an
    * object class instance.
    *
    * @return set containing the names of all of the attributes of an
    * object class instance
    */
    public Set< String > getAllAttributeNames() {
        return get_all_attribute_names();
    }

    @Override
    public String getAttributeName(int datamemberHandle) {
        if (datamemberHandle == _Break_handle) return "Break";
        else if (datamemberHandle == _PhysicsTime_handle) return "PhysicsTime";
        else if (datamemberHandle == _Protocol_handle) return "Protocol";
        else if (datamemberHandle == _otherPhysicsSignals_handle) return "otherPhysicsSignals";
        else if (datamemberHandle == _vehicleSpeed_handle) return "vehicleSpeed";
        else if (datamemberHandle == _vehicleSpeedAhead_handle) return "vehicleSpeedAhead";
        else return super.getAttributeName(datamemberHandle);
    }

    /**
    * Publishes the object class of this instance of the class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void publishObject(RTIambassador rti) {
        publish(rti);
    }

    /**
    * Unpublishes the object class of this instance of this class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unpublishObject(RTIambassador rti) {
        unpublish(rti);
    }

    /**
    * Subscribes a federate to the object class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void subscribeObject(RTIambassador rti) {
        subscribe(rti);
    }

    /**
    * Unsubscribes a federate from the object class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unsubscribeObject(RTIambassador rti) {
        unsubscribe(rti);
    }

    @Override
    public String toString() {
        return getClass().getName() + "("
                + "Break:" + get_Break()
                + "," + "PhysicsTime:" + get_PhysicsTime()
                + "," + "Protocol:" + get_Protocol()
                + "," + "otherPhysicsSignals:" + get_otherPhysicsSignals()
                + "," + "vehicleSpeed:" + get_vehicleSpeed()
                + "," + "vehicleSpeedAhead:" + get_vehicleSpeedAhead()
                + ")";
    }


    /**
    * Publishes the "Break" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Break" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_Break() {
        _publishAttributeNameSet.add( "Break" );
    }

    /**
    * Unpublishes the "Break" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Break" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_Break() {
        _publishAttributeNameSet.remove( "Break" );
    }

    /**
    * Subscribes a federate to the "Break" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Break" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_Break() {
        _subscribeAttributeNameSet.add( "Break" );
    }

    /**
    * Unsubscribes a federate from the "Break" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Break" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_Break() {
        _subscribeAttributeNameSet.remove( "Break" );
    }

    /**
    * Publishes the "PhysicsTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "PhysicsTime" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_PhysicsTime() {
        _publishAttributeNameSet.add( "PhysicsTime" );
    }

    /**
    * Unpublishes the "PhysicsTime" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "PhysicsTime" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_PhysicsTime() {
        _publishAttributeNameSet.remove( "PhysicsTime" );
    }

    /**
    * Subscribes a federate to the "PhysicsTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "PhysicsTime" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_PhysicsTime() {
        _subscribeAttributeNameSet.add( "PhysicsTime" );
    }

    /**
    * Unsubscribes a federate from the "PhysicsTime" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "PhysicsTime" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_PhysicsTime() {
        _subscribeAttributeNameSet.remove( "PhysicsTime" );
    }

    /**
    * Publishes the "Protocol" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Protocol" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_Protocol() {
        _publishAttributeNameSet.add( "Protocol" );
    }

    /**
    * Unpublishes the "Protocol" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Protocol" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_Protocol() {
        _publishAttributeNameSet.remove( "Protocol" );
    }

    /**
    * Subscribes a federate to the "Protocol" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Protocol" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_Protocol() {
        _subscribeAttributeNameSet.add( "Protocol" );
    }

    /**
    * Unsubscribes a federate from the "Protocol" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Protocol" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_Protocol() {
        _subscribeAttributeNameSet.remove( "Protocol" );
    }

    /**
    * Publishes the "otherPhysicsSignals" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "otherPhysicsSignals" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_otherPhysicsSignals() {
        _publishAttributeNameSet.add( "otherPhysicsSignals" );
    }

    /**
    * Unpublishes the "otherPhysicsSignals" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "otherPhysicsSignals" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_otherPhysicsSignals() {
        _publishAttributeNameSet.remove( "otherPhysicsSignals" );
    }

    /**
    * Subscribes a federate to the "otherPhysicsSignals" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "otherPhysicsSignals" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_otherPhysicsSignals() {
        _subscribeAttributeNameSet.add( "otherPhysicsSignals" );
    }

    /**
    * Unsubscribes a federate from the "otherPhysicsSignals" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "otherPhysicsSignals" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_otherPhysicsSignals() {
        _subscribeAttributeNameSet.remove( "otherPhysicsSignals" );
    }

    /**
    * Publishes the "vehicleSpeed" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "vehicleSpeed" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_vehicleSpeed() {
        _publishAttributeNameSet.add( "vehicleSpeed" );
    }

    /**
    * Unpublishes the "vehicleSpeed" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "vehicleSpeed" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_vehicleSpeed() {
        _publishAttributeNameSet.remove( "vehicleSpeed" );
    }

    /**
    * Subscribes a federate to the "vehicleSpeed" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "vehicleSpeed" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_vehicleSpeed() {
        _subscribeAttributeNameSet.add( "vehicleSpeed" );
    }

    /**
    * Unsubscribes a federate from the "vehicleSpeed" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "vehicleSpeed" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_vehicleSpeed() {
        _subscribeAttributeNameSet.remove( "vehicleSpeed" );
    }

    /**
    * Publishes the "vehicleSpeedAhead" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "vehicleSpeedAhead" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_vehicleSpeedAhead() {
        _publishAttributeNameSet.add( "vehicleSpeedAhead" );
    }

    /**
    * Unpublishes the "vehicleSpeedAhead" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "vehicleSpeedAhead" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_vehicleSpeedAhead() {
        _publishAttributeNameSet.remove( "vehicleSpeedAhead" );
    }

    /**
    * Subscribes a federate to the "vehicleSpeedAhead" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "vehicleSpeedAhead" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_vehicleSpeedAhead() {
        _subscribeAttributeNameSet.add( "vehicleSpeedAhead" );
    }

    /**
    * Unsubscribes a federate from the "vehicleSpeedAhead" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "vehicleSpeedAhead" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_vehicleSpeedAhead() {
        _subscribeAttributeNameSet.remove( "vehicleSpeedAhead" );
    }

    protected Attribute< String > _Break =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "Break" attribute to "value" for this object.
    *
    * @param value the new value for the "Break" attribute
    */
    public void set_Break( String value ) {
        _Break.setValue( value );
        _Break.setTime( getTime() );
    }

    /**
    * Returns the value of the "Break" attribute of this object.
    *
    * @return the value of the "Break" attribute
    */
    public String get_Break() {
        return _Break.getValue();
    }

    /**
    * Returns the current timestamp of the "Break" attribute of this object.
    *
    * @return the current timestamp of the "Break" attribute
    */
    public double get_Break_time() {
        return _Break.getTime();
    }

    protected Attribute< String > _PhysicsTime =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "PhysicsTime" attribute to "value" for this object.
    *
    * @param value the new value for the "PhysicsTime" attribute
    */
    public void set_PhysicsTime( String value ) {
        _PhysicsTime.setValue( value );
        _PhysicsTime.setTime( getTime() );
    }

    /**
    * Returns the value of the "PhysicsTime" attribute of this object.
    *
    * @return the value of the "PhysicsTime" attribute
    */
    public String get_PhysicsTime() {
        return _PhysicsTime.getValue();
    }

    /**
    * Returns the current timestamp of the "PhysicsTime" attribute of this object.
    *
    * @return the current timestamp of the "PhysicsTime" attribute
    */
    public double get_PhysicsTime_time() {
        return _PhysicsTime.getTime();
    }

    protected Attribute< String > _Protocol =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "Protocol" attribute to "value" for this object.
    *
    * @param value the new value for the "Protocol" attribute
    */
    public void set_Protocol( String value ) {
        _Protocol.setValue( value );
        _Protocol.setTime( getTime() );
    }

    /**
    * Returns the value of the "Protocol" attribute of this object.
    *
    * @return the value of the "Protocol" attribute
    */
    public String get_Protocol() {
        return _Protocol.getValue();
    }

    /**
    * Returns the current timestamp of the "Protocol" attribute of this object.
    *
    * @return the current timestamp of the "Protocol" attribute
    */
    public double get_Protocol_time() {
        return _Protocol.getTime();
    }

    protected Attribute< String > _otherPhysicsSignals =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "otherPhysicsSignals" attribute to "value" for this object.
    *
    * @param value the new value for the "otherPhysicsSignals" attribute
    */
    public void set_otherPhysicsSignals( String value ) {
        _otherPhysicsSignals.setValue( value );
        _otherPhysicsSignals.setTime( getTime() );
    }

    /**
    * Returns the value of the "otherPhysicsSignals" attribute of this object.
    *
    * @return the value of the "otherPhysicsSignals" attribute
    */
    public String get_otherPhysicsSignals() {
        return _otherPhysicsSignals.getValue();
    }

    /**
    * Returns the current timestamp of the "otherPhysicsSignals" attribute of this object.
    *
    * @return the current timestamp of the "otherPhysicsSignals" attribute
    */
    public double get_otherPhysicsSignals_time() {
        return _otherPhysicsSignals.getTime();
    }

    protected Attribute< String > _vehicleSpeed =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "vehicleSpeed" attribute to "value" for this object.
    *
    * @param value the new value for the "vehicleSpeed" attribute
    */
    public void set_vehicleSpeed( String value ) {
        _vehicleSpeed.setValue( value );
        _vehicleSpeed.setTime( getTime() );
    }

    /**
    * Returns the value of the "vehicleSpeed" attribute of this object.
    *
    * @return the value of the "vehicleSpeed" attribute
    */
    public String get_vehicleSpeed() {
        return _vehicleSpeed.getValue();
    }

    /**
    * Returns the current timestamp of the "vehicleSpeed" attribute of this object.
    *
    * @return the current timestamp of the "vehicleSpeed" attribute
    */
    public double get_vehicleSpeed_time() {
        return _vehicleSpeed.getTime();
    }

    protected Attribute< String > _vehicleSpeedAhead =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "vehicleSpeedAhead" attribute to "value" for this object.
    *
    * @param value the new value for the "vehicleSpeedAhead" attribute
    */
    public void set_vehicleSpeedAhead( String value ) {
        _vehicleSpeedAhead.setValue( value );
        _vehicleSpeedAhead.setTime( getTime() );
    }

    /**
    * Returns the value of the "vehicleSpeedAhead" attribute of this object.
    *
    * @return the value of the "vehicleSpeedAhead" attribute
    */
    public String get_vehicleSpeedAhead() {
        return _vehicleSpeedAhead.getValue();
    }

    /**
    * Returns the current timestamp of the "vehicleSpeedAhead" attribute of this object.
    *
    * @return the current timestamp of the "vehicleSpeedAhead" attribute
    */
    public double get_vehicleSpeedAhead_time() {
        return _vehicleSpeedAhead.getTime();
    }

    protected VehiclePhysicsState( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected VehiclePhysicsState( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the VehiclePhysicsState object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new VehiclePhysicsState object class instance
    */
    public VehiclePhysicsState( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #VehiclePhysicsState( ReflectedAttributes datamemberMap )}, except this
    * new VehiclePhysicsState object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new VehiclePhysicsState object class instance
    * @param logicalTime timestamp for this new VehiclePhysicsState object class
    * instance
    */
    public VehiclePhysicsState( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new VehiclePhysicsState object class instance that is a duplicate
    * of the instance referred to by VehiclePhysicsState_var.
    *
    * @param VehiclePhysicsState_var VehiclePhysicsState object class instance of which
    * this newly created VehiclePhysicsState object class instance will be a
    * duplicate
    */
    public VehiclePhysicsState( VehiclePhysicsState VehiclePhysicsState_var ) {
        super( VehiclePhysicsState_var );

        set_Break( VehiclePhysicsState_var.get_Break() );
        set_PhysicsTime( VehiclePhysicsState_var.get_PhysicsTime() );
        set_Protocol( VehiclePhysicsState_var.get_Protocol() );
        set_otherPhysicsSignals( VehiclePhysicsState_var.get_otherPhysicsSignals() );
        set_vehicleSpeed( VehiclePhysicsState_var.get_vehicleSpeed() );
        set_vehicleSpeedAhead( VehiclePhysicsState_var.get_vehicleSpeedAhead() );
    }

    /**
    * Returns the value of the attribute whose name is "datamemberName"
    * for this object.
    *
    * @param datamemberName name of attribute whose value is to be
    * returned
    * @return value of the attribute whose name is "datamemberName"
    * for this object
    */
    public Object getAttribute( String datamemberName ) {
        if ( "Break".equals(datamemberName) ) return get_Break();
        else if ( "PhysicsTime".equals(datamemberName) ) return get_PhysicsTime();
        else if ( "Protocol".equals(datamemberName) ) return get_Protocol();
        else if ( "otherPhysicsSignals".equals(datamemberName) ) return get_otherPhysicsSignals();
        else if ( "vehicleSpeed".equals(datamemberName) ) return get_vehicleSpeed();
        else if ( "vehicleSpeedAhead".equals(datamemberName) ) return get_vehicleSpeedAhead();
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "Break".equals( datamemberName) ) set_Break( val );
        else if ( "PhysicsTime".equals( datamemberName) ) set_PhysicsTime( val );
        else if ( "Protocol".equals( datamemberName) ) set_Protocol( val );
        else if ( "otherPhysicsSignals".equals( datamemberName) ) set_otherPhysicsSignals( val );
        else if ( "vehicleSpeed".equals( datamemberName) ) set_vehicleSpeed( val );
        else if ( "vehicleSpeedAhead".equals( datamemberName) ) set_vehicleSpeedAhead( val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "Break".equals( datamemberName) ) set_Break( (String)val );
        else if ( "PhysicsTime".equals( datamemberName) ) set_PhysicsTime( (String)val );
        else if ( "Protocol".equals( datamemberName) ) set_Protocol( (String)val );
        else if ( "otherPhysicsSignals".equals( datamemberName) ) set_otherPhysicsSignals( (String)val );
        else if ( "vehicleSpeed".equals( datamemberName) ) set_vehicleSpeed( (String)val );
        else if ( "vehicleSpeedAhead".equals( datamemberName) ) set_vehicleSpeedAhead( (String)val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    @Override
    protected SuppliedAttributes createSuppliedDatamembers(boolean force) {
        SuppliedAttributes datamembers = _factory.createSuppliedAttributes();
 
        if (_publishAttributeNameSet.contains("Break") && _Break.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("Break"), getAttribute("Break").toString().getBytes() );
            _Break.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("PhysicsTime") && _PhysicsTime.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("PhysicsTime"), getAttribute("PhysicsTime").toString().getBytes() );
            _PhysicsTime.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("Protocol") && _Protocol.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("Protocol"), getAttribute("Protocol").toString().getBytes() );
            _Protocol.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("otherPhysicsSignals") && _otherPhysicsSignals.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("otherPhysicsSignals"), getAttribute("otherPhysicsSignals").toString().getBytes() );
            _otherPhysicsSignals.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("vehicleSpeed") && _vehicleSpeed.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("vehicleSpeed"), getAttribute("vehicleSpeed").toString().getBytes() );
            _vehicleSpeed.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("vehicleSpeedAhead") && _vehicleSpeedAhead.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("vehicleSpeedAhead"), getAttribute("vehicleSpeedAhead").toString().getBytes() );
            _vehicleSpeedAhead.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof VehiclePhysicsState ) {
            VehiclePhysicsState data = (VehiclePhysicsState)object;
            _Break = data._Break;
            _PhysicsTime = data._PhysicsTime;
            _Protocol = data._Protocol;
            _otherPhysicsSignals = data._otherPhysicsSignals;
            _vehicleSpeed = data._vehicleSpeed;
            _vehicleSpeedAhead = data._vehicleSpeedAhead;
        }
    }
}


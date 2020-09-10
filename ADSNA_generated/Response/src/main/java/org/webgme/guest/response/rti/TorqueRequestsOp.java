package org.webgme.guest.response.rti;

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
* Implements ObjectRoot.TorqueRequestsOp
*/
public class TorqueRequestsOp extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the TorqueRequestsOp object class with default attribute values.
    */
    public TorqueRequestsOp() {}

    private static int _Attribute_handle;
    private static int _Protocol_handle;
    private static int _speed_request_handle;
    private static int _speed_request_ahead_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the TorqueRequestsOp object class.
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
    * Returns the fully-qualified (dot-delimited) name of the TorqueRequestsOp object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.TorqueRequestsOp";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the TorqueRequestsOp object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "TorqueRequestsOp";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * TorqueRequestsOp object class.
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
    * TorqueRequestsOp object class.
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
        _classNameSet.add("ObjectRoot.TorqueRequestsOp");
        _classNameClassMap.put("ObjectRoot.TorqueRequestsOp", TorqueRequestsOp.class);

        _datamemberClassNameSetMap.put("ObjectRoot.TorqueRequestsOp", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.TorqueRequestsOp", _allDatamemberNames);

        _datamemberNames.add("Attribute");
        _datamemberNames.add("Protocol");
        _datamemberNames.add("speed_request");
        _datamemberNames.add("speed_request_ahead");

        _datamemberTypeMap.put("Attribute", "String");
        _datamemberTypeMap.put("Protocol", "String");
        _datamemberTypeMap.put("speed_request", "String");
        _datamemberTypeMap.put("speed_request_ahead", "String");

        _allDatamemberNames.add("Attribute");
        _allDatamemberNames.add("Protocol");
        _allDatamemberNames.add("speed_request");
        _allDatamemberNames.add("speed_request_ahead");

        _classNamePublishAttributeNameMap.put("ObjectRoot.TorqueRequestsOp", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.TorqueRequestsOp", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.TorqueRequestsOp");
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

        _classNameHandleMap.put("ObjectRoot.TorqueRequestsOp", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.TorqueRequestsOp");
        _classHandleSimpleNameMap.put(get_handle(), "TorqueRequestsOp");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _Attribute_handle = rti.getAttributeHandle("Attribute", get_handle());
                _Protocol_handle = rti.getAttributeHandle("Protocol", get_handle());
                _speed_request_handle = rti.getAttributeHandle("speed_request", get_handle());
                _speed_request_ahead_handle = rti.getAttributeHandle("speed_request_ahead", get_handle());
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

        _datamemberNameHandleMap.put("ObjectRoot.TorqueRequestsOp.Attribute", _Attribute_handle);
        _datamemberNameHandleMap.put("ObjectRoot.TorqueRequestsOp.Protocol", _Protocol_handle);
        _datamemberNameHandleMap.put("ObjectRoot.TorqueRequestsOp.speed_request", _speed_request_handle);
        _datamemberNameHandleMap.put("ObjectRoot.TorqueRequestsOp.speed_request_ahead", _speed_request_ahead_handle);

        _datamemberHandleNameMap.put(_Attribute_handle, "Attribute");
        _datamemberHandleNameMap.put(_Protocol_handle, "Protocol");
        _datamemberHandleNameMap.put(_speed_request_handle, "speed_request");
        _datamemberHandleNameMap.put(_speed_request_ahead_handle, "speed_request_ahead");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the TorqueRequestsOp object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.TorqueRequestsOp." + attributeName));
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
    * Unpublishes the TorqueRequestsOp object class for a federate.
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
    * Subscribes a federate to the TorqueRequestsOp object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.TorqueRequestsOp." + attributeName));
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
    * Unsubscribes a federate from the TorqueRequestsOp object class.
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
    * (that is, the TorqueRequestsOp object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the TorqueRequestsOp object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the TorqueRequestsOp object class).
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
        if (datamemberHandle == _Attribute_handle) return "Attribute";
        else if (datamemberHandle == _Protocol_handle) return "Protocol";
        else if (datamemberHandle == _speed_request_handle) return "speed_request";
        else if (datamemberHandle == _speed_request_ahead_handle) return "speed_request_ahead";
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
                + "Attribute:" + get_Attribute()
                + "," + "Protocol:" + get_Protocol()
                + "," + "speed_request:" + get_speed_request()
                + "," + "speed_request_ahead:" + get_speed_request_ahead()
                + ")";
    }


    /**
    * Publishes the "Attribute" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Attribute" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_Attribute() {
        _publishAttributeNameSet.add( "Attribute" );
    }

    /**
    * Unpublishes the "Attribute" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Attribute" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_Attribute() {
        _publishAttributeNameSet.remove( "Attribute" );
    }

    /**
    * Subscribes a federate to the "Attribute" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Attribute" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_Attribute() {
        _subscribeAttributeNameSet.add( "Attribute" );
    }

    /**
    * Unsubscribes a federate from the "Attribute" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Attribute" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_Attribute() {
        _subscribeAttributeNameSet.remove( "Attribute" );
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
    * Publishes the "speed_request" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "speed_request" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_speed_request() {
        _publishAttributeNameSet.add( "speed_request" );
    }

    /**
    * Unpublishes the "speed_request" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "speed_request" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_speed_request() {
        _publishAttributeNameSet.remove( "speed_request" );
    }

    /**
    * Subscribes a federate to the "speed_request" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "speed_request" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_speed_request() {
        _subscribeAttributeNameSet.add( "speed_request" );
    }

    /**
    * Unsubscribes a federate from the "speed_request" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "speed_request" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_speed_request() {
        _subscribeAttributeNameSet.remove( "speed_request" );
    }

    /**
    * Publishes the "speed_request_ahead" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "speed_request_ahead" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_speed_request_ahead() {
        _publishAttributeNameSet.add( "speed_request_ahead" );
    }

    /**
    * Unpublishes the "speed_request_ahead" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "speed_request_ahead" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_speed_request_ahead() {
        _publishAttributeNameSet.remove( "speed_request_ahead" );
    }

    /**
    * Subscribes a federate to the "speed_request_ahead" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "speed_request_ahead" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_speed_request_ahead() {
        _subscribeAttributeNameSet.add( "speed_request_ahead" );
    }

    /**
    * Unsubscribes a federate from the "speed_request_ahead" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "speed_request_ahead" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_speed_request_ahead() {
        _subscribeAttributeNameSet.remove( "speed_request_ahead" );
    }

    protected Attribute< String > _Attribute =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "Attribute" attribute to "value" for this object.
    *
    * @param value the new value for the "Attribute" attribute
    */
    public void set_Attribute( String value ) {
        _Attribute.setValue( value );
        _Attribute.setTime( getTime() );
    }

    /**
    * Returns the value of the "Attribute" attribute of this object.
    *
    * @return the value of the "Attribute" attribute
    */
    public String get_Attribute() {
        return _Attribute.getValue();
    }

    /**
    * Returns the current timestamp of the "Attribute" attribute of this object.
    *
    * @return the current timestamp of the "Attribute" attribute
    */
    public double get_Attribute_time() {
        return _Attribute.getTime();
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

    protected Attribute< String > _speed_request =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "speed_request" attribute to "value" for this object.
    *
    * @param value the new value for the "speed_request" attribute
    */
    public void set_speed_request( String value ) {
        _speed_request.setValue( value );
        _speed_request.setTime( getTime() );
    }

    /**
    * Returns the value of the "speed_request" attribute of this object.
    *
    * @return the value of the "speed_request" attribute
    */
    public String get_speed_request() {
        return _speed_request.getValue();
    }

    /**
    * Returns the current timestamp of the "speed_request" attribute of this object.
    *
    * @return the current timestamp of the "speed_request" attribute
    */
    public double get_speed_request_time() {
        return _speed_request.getTime();
    }

    protected Attribute< String > _speed_request_ahead =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "speed_request_ahead" attribute to "value" for this object.
    *
    * @param value the new value for the "speed_request_ahead" attribute
    */
    public void set_speed_request_ahead( String value ) {
        _speed_request_ahead.setValue( value );
        _speed_request_ahead.setTime( getTime() );
    }

    /**
    * Returns the value of the "speed_request_ahead" attribute of this object.
    *
    * @return the value of the "speed_request_ahead" attribute
    */
    public String get_speed_request_ahead() {
        return _speed_request_ahead.getValue();
    }

    /**
    * Returns the current timestamp of the "speed_request_ahead" attribute of this object.
    *
    * @return the current timestamp of the "speed_request_ahead" attribute
    */
    public double get_speed_request_ahead_time() {
        return _speed_request_ahead.getTime();
    }

    protected TorqueRequestsOp( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected TorqueRequestsOp( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the TorqueRequestsOp object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new TorqueRequestsOp object class instance
    */
    public TorqueRequestsOp( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #TorqueRequestsOp( ReflectedAttributes datamemberMap )}, except this
    * new TorqueRequestsOp object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new TorqueRequestsOp object class instance
    * @param logicalTime timestamp for this new TorqueRequestsOp object class
    * instance
    */
    public TorqueRequestsOp( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new TorqueRequestsOp object class instance that is a duplicate
    * of the instance referred to by TorqueRequestsOp_var.
    *
    * @param TorqueRequestsOp_var TorqueRequestsOp object class instance of which
    * this newly created TorqueRequestsOp object class instance will be a
    * duplicate
    */
    public TorqueRequestsOp( TorqueRequestsOp TorqueRequestsOp_var ) {
        super( TorqueRequestsOp_var );

        set_Attribute( TorqueRequestsOp_var.get_Attribute() );
        set_Protocol( TorqueRequestsOp_var.get_Protocol() );
        set_speed_request( TorqueRequestsOp_var.get_speed_request() );
        set_speed_request_ahead( TorqueRequestsOp_var.get_speed_request_ahead() );
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
        if ( "Attribute".equals(datamemberName) ) return get_Attribute();
        else if ( "Protocol".equals(datamemberName) ) return get_Protocol();
        else if ( "speed_request".equals(datamemberName) ) return get_speed_request();
        else if ( "speed_request_ahead".equals(datamemberName) ) return get_speed_request_ahead();
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "Attribute".equals( datamemberName) ) set_Attribute( val );
        else if ( "Protocol".equals( datamemberName) ) set_Protocol( val );
        else if ( "speed_request".equals( datamemberName) ) set_speed_request( val );
        else if ( "speed_request_ahead".equals( datamemberName) ) set_speed_request_ahead( val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "Attribute".equals( datamemberName) ) set_Attribute( (String)val );
        else if ( "Protocol".equals( datamemberName) ) set_Protocol( (String)val );
        else if ( "speed_request".equals( datamemberName) ) set_speed_request( (String)val );
        else if ( "speed_request_ahead".equals( datamemberName) ) set_speed_request_ahead( (String)val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    @Override
    protected SuppliedAttributes createSuppliedDatamembers(boolean force) {
        SuppliedAttributes datamembers = _factory.createSuppliedAttributes();
 
        if (_publishAttributeNameSet.contains("Attribute") && _Attribute.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("Attribute"), getAttribute("Attribute").toString().getBytes() );
            _Attribute.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("Protocol") && _Protocol.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("Protocol"), getAttribute("Protocol").toString().getBytes() );
            _Protocol.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("speed_request") && _speed_request.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("speed_request"), getAttribute("speed_request").toString().getBytes() );
            _speed_request.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("speed_request_ahead") && _speed_request_ahead.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("speed_request_ahead"), getAttribute("speed_request_ahead").toString().getBytes() );
            _speed_request_ahead.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof TorqueRequestsOp ) {
            TorqueRequestsOp data = (TorqueRequestsOp)object;
            _Attribute = data._Attribute;
            _Protocol = data._Protocol;
            _speed_request = data._speed_request;
            _speed_request_ahead = data._speed_request_ahead;
        }
    }
}


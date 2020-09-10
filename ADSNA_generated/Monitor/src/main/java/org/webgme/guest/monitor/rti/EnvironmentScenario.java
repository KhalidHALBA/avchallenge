package org.webgme.guest.monitor.rti;

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
* Implements ObjectRoot.EnvironmentScenario
*/
public class EnvironmentScenario extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the EnvironmentScenario object class with default attribute values.
    */
    public EnvironmentScenario() {}

    private static int _Protocol_handle;
    private static int _obstacle_presence_handle;
    private static int _obstacle_presence_ahead_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the EnvironmentScenario object class.
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
    * Returns the fully-qualified (dot-delimited) name of the EnvironmentScenario object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.EnvironmentScenario";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the EnvironmentScenario object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "EnvironmentScenario";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * EnvironmentScenario object class.
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
    * EnvironmentScenario object class.
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
        _classNameSet.add("ObjectRoot.EnvironmentScenario");
        _classNameClassMap.put("ObjectRoot.EnvironmentScenario", EnvironmentScenario.class);

        _datamemberClassNameSetMap.put("ObjectRoot.EnvironmentScenario", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.EnvironmentScenario", _allDatamemberNames);

        _datamemberNames.add("Protocol");
        _datamemberNames.add("obstacle_presence");
        _datamemberNames.add("obstacle_presence_ahead");

        _datamemberTypeMap.put("Protocol", "String");
        _datamemberTypeMap.put("obstacle_presence", "String");
        _datamemberTypeMap.put("obstacle_presence_ahead", "String");

        _allDatamemberNames.add("Protocol");
        _allDatamemberNames.add("obstacle_presence");
        _allDatamemberNames.add("obstacle_presence_ahead");

        _classNamePublishAttributeNameMap.put("ObjectRoot.EnvironmentScenario", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.EnvironmentScenario", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.EnvironmentScenario");
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

        _classNameHandleMap.put("ObjectRoot.EnvironmentScenario", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.EnvironmentScenario");
        _classHandleSimpleNameMap.put(get_handle(), "EnvironmentScenario");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _Protocol_handle = rti.getAttributeHandle("Protocol", get_handle());
                _obstacle_presence_handle = rti.getAttributeHandle("obstacle_presence", get_handle());
                _obstacle_presence_ahead_handle = rti.getAttributeHandle("obstacle_presence_ahead", get_handle());
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

        _datamemberNameHandleMap.put("ObjectRoot.EnvironmentScenario.Protocol", _Protocol_handle);
        _datamemberNameHandleMap.put("ObjectRoot.EnvironmentScenario.obstacle_presence", _obstacle_presence_handle);
        _datamemberNameHandleMap.put("ObjectRoot.EnvironmentScenario.obstacle_presence_ahead", _obstacle_presence_ahead_handle);

        _datamemberHandleNameMap.put(_Protocol_handle, "Protocol");
        _datamemberHandleNameMap.put(_obstacle_presence_handle, "obstacle_presence");
        _datamemberHandleNameMap.put(_obstacle_presence_ahead_handle, "obstacle_presence_ahead");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the EnvironmentScenario object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.EnvironmentScenario." + attributeName));
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
    * Unpublishes the EnvironmentScenario object class for a federate.
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
    * Subscribes a federate to the EnvironmentScenario object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.EnvironmentScenario." + attributeName));
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
    * Unsubscribes a federate from the EnvironmentScenario object class.
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
    * (that is, the EnvironmentScenario object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the EnvironmentScenario object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the EnvironmentScenario object class).
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
        if (datamemberHandle == _Protocol_handle) return "Protocol";
        else if (datamemberHandle == _obstacle_presence_handle) return "obstacle_presence";
        else if (datamemberHandle == _obstacle_presence_ahead_handle) return "obstacle_presence_ahead";
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
                + "Protocol:" + get_Protocol()
                + "," + "obstacle_presence:" + get_obstacle_presence()
                + "," + "obstacle_presence_ahead:" + get_obstacle_presence_ahead()
                + ")";
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
    * Publishes the "obstacle_presence" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "obstacle_presence" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_obstacle_presence() {
        _publishAttributeNameSet.add( "obstacle_presence" );
    }

    /**
    * Unpublishes the "obstacle_presence" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "obstacle_presence" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_obstacle_presence() {
        _publishAttributeNameSet.remove( "obstacle_presence" );
    }

    /**
    * Subscribes a federate to the "obstacle_presence" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "obstacle_presence" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_obstacle_presence() {
        _subscribeAttributeNameSet.add( "obstacle_presence" );
    }

    /**
    * Unsubscribes a federate from the "obstacle_presence" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "obstacle_presence" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_obstacle_presence() {
        _subscribeAttributeNameSet.remove( "obstacle_presence" );
    }

    /**
    * Publishes the "obstacle_presence_ahead" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "obstacle_presence_ahead" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_obstacle_presence_ahead() {
        _publishAttributeNameSet.add( "obstacle_presence_ahead" );
    }

    /**
    * Unpublishes the "obstacle_presence_ahead" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "obstacle_presence_ahead" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_obstacle_presence_ahead() {
        _publishAttributeNameSet.remove( "obstacle_presence_ahead" );
    }

    /**
    * Subscribes a federate to the "obstacle_presence_ahead" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "obstacle_presence_ahead" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_obstacle_presence_ahead() {
        _subscribeAttributeNameSet.add( "obstacle_presence_ahead" );
    }

    /**
    * Unsubscribes a federate from the "obstacle_presence_ahead" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "obstacle_presence_ahead" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_obstacle_presence_ahead() {
        _subscribeAttributeNameSet.remove( "obstacle_presence_ahead" );
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

    protected Attribute< String > _obstacle_presence =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "obstacle_presence" attribute to "value" for this object.
    *
    * @param value the new value for the "obstacle_presence" attribute
    */
    public void set_obstacle_presence( String value ) {
        _obstacle_presence.setValue( value );
        _obstacle_presence.setTime( getTime() );
    }

    /**
    * Returns the value of the "obstacle_presence" attribute of this object.
    *
    * @return the value of the "obstacle_presence" attribute
    */
    public String get_obstacle_presence() {
        return _obstacle_presence.getValue();
    }

    /**
    * Returns the current timestamp of the "obstacle_presence" attribute of this object.
    *
    * @return the current timestamp of the "obstacle_presence" attribute
    */
    public double get_obstacle_presence_time() {
        return _obstacle_presence.getTime();
    }

    protected Attribute< String > _obstacle_presence_ahead =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "obstacle_presence_ahead" attribute to "value" for this object.
    *
    * @param value the new value for the "obstacle_presence_ahead" attribute
    */
    public void set_obstacle_presence_ahead( String value ) {
        _obstacle_presence_ahead.setValue( value );
        _obstacle_presence_ahead.setTime( getTime() );
    }

    /**
    * Returns the value of the "obstacle_presence_ahead" attribute of this object.
    *
    * @return the value of the "obstacle_presence_ahead" attribute
    */
    public String get_obstacle_presence_ahead() {
        return _obstacle_presence_ahead.getValue();
    }

    /**
    * Returns the current timestamp of the "obstacle_presence_ahead" attribute of this object.
    *
    * @return the current timestamp of the "obstacle_presence_ahead" attribute
    */
    public double get_obstacle_presence_ahead_time() {
        return _obstacle_presence_ahead.getTime();
    }

    protected EnvironmentScenario( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected EnvironmentScenario( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the EnvironmentScenario object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new EnvironmentScenario object class instance
    */
    public EnvironmentScenario( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #EnvironmentScenario( ReflectedAttributes datamemberMap )}, except this
    * new EnvironmentScenario object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new EnvironmentScenario object class instance
    * @param logicalTime timestamp for this new EnvironmentScenario object class
    * instance
    */
    public EnvironmentScenario( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new EnvironmentScenario object class instance that is a duplicate
    * of the instance referred to by EnvironmentScenario_var.
    *
    * @param EnvironmentScenario_var EnvironmentScenario object class instance of which
    * this newly created EnvironmentScenario object class instance will be a
    * duplicate
    */
    public EnvironmentScenario( EnvironmentScenario EnvironmentScenario_var ) {
        super( EnvironmentScenario_var );

        set_Protocol( EnvironmentScenario_var.get_Protocol() );
        set_obstacle_presence( EnvironmentScenario_var.get_obstacle_presence() );
        set_obstacle_presence_ahead( EnvironmentScenario_var.get_obstacle_presence_ahead() );
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
        if ( "Protocol".equals(datamemberName) ) return get_Protocol();
        else if ( "obstacle_presence".equals(datamemberName) ) return get_obstacle_presence();
        else if ( "obstacle_presence_ahead".equals(datamemberName) ) return get_obstacle_presence_ahead();
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "Protocol".equals( datamemberName) ) set_Protocol( val );
        else if ( "obstacle_presence".equals( datamemberName) ) set_obstacle_presence( val );
        else if ( "obstacle_presence_ahead".equals( datamemberName) ) set_obstacle_presence_ahead( val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "Protocol".equals( datamemberName) ) set_Protocol( (String)val );
        else if ( "obstacle_presence".equals( datamemberName) ) set_obstacle_presence( (String)val );
        else if ( "obstacle_presence_ahead".equals( datamemberName) ) set_obstacle_presence_ahead( (String)val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    @Override
    protected SuppliedAttributes createSuppliedDatamembers(boolean force) {
        SuppliedAttributes datamembers = _factory.createSuppliedAttributes();
 
        if (_publishAttributeNameSet.contains("Protocol") && _Protocol.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("Protocol"), getAttribute("Protocol").toString().getBytes() );
            _Protocol.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("obstacle_presence") && _obstacle_presence.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("obstacle_presence"), getAttribute("obstacle_presence").toString().getBytes() );
            _obstacle_presence.setHasBeenUpdated();
        }

        if (_publishAttributeNameSet.contains("obstacle_presence_ahead") && _obstacle_presence_ahead.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("obstacle_presence_ahead"), getAttribute("obstacle_presence_ahead").toString().getBytes() );
            _obstacle_presence_ahead.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof EnvironmentScenario ) {
            EnvironmentScenario data = (EnvironmentScenario)object;
            _Protocol = data._Protocol;
            _obstacle_presence = data._obstacle_presence;
            _obstacle_presence_ahead = data._obstacle_presence_ahead;
        }
    }
}


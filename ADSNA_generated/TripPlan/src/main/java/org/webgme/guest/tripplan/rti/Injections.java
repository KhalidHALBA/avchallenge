package org.webgme.guest.tripplan.rti;

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
* Implements ObjectRoot.Injections
*/
public class Injections extends ObjectRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the Injections object class with default attribute values.
    */
    public Injections() {}

    private static int _Protocol_handle;
    private static int _Value_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the Injections object class.
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
    * Returns the fully-qualified (dot-delimited) name of the Injections object class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this object class
    */
    public static String get_class_name() {
        return "ObjectRoot.Injections";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the Injections object class.
    *
    * @return the name of this object class
    */
    public static String get_simple_class_name() {
        return "Injections";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden attributes in the
    * Injections object class.
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
    * Injections object class.
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
        _classNameSet.add("ObjectRoot.Injections");
        _classNameClassMap.put("ObjectRoot.Injections", Injections.class);

        _datamemberClassNameSetMap.put("ObjectRoot.Injections", _datamemberNames);
        _allDatamemberClassNameSetMap.put("ObjectRoot.Injections", _allDatamemberNames);

        _datamemberNames.add("Protocol");
        _datamemberNames.add("Value");

        _datamemberTypeMap.put("Protocol", "String");
        _datamemberTypeMap.put("Value", "String");

        _allDatamemberNames.add("Protocol");
        _allDatamemberNames.add("Value");

        _classNamePublishAttributeNameMap.put("ObjectRoot.Injections", _publishAttributeNameSet);
        _classNameSubscribeAttributeNameMap.put("ObjectRoot.Injections", _subscribeAttributeNameSet);
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        ObjectRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getObjectClassHandle("ObjectRoot.Injections");
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

        _classNameHandleMap.put("ObjectRoot.Injections", get_handle());
        _classHandleNameMap.put(get_handle(), "ObjectRoot.Injections");
        _classHandleSimpleNameMap.put(get_handle(), "Injections");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _Protocol_handle = rti.getAttributeHandle("Protocol", get_handle());
                _Value_handle = rti.getAttributeHandle("Value", get_handle());
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

        _datamemberNameHandleMap.put("ObjectRoot.Injections.Protocol", _Protocol_handle);
        _datamemberNameHandleMap.put("ObjectRoot.Injections.Value", _Value_handle);

        _datamemberHandleNameMap.put(_Protocol_handle, "Protocol");
        _datamemberHandleNameMap.put(_Value_handle, "Value");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the Injections object class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        AttributeHandleSet publishedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _publishAttributeNameSet) {
            try {
                publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.Injections." + attributeName));
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
    * Unpublishes the Injections object class for a federate.
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
    * Subscribes a federate to the Injections object class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        AttributeHandleSet subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
        for(String attributeName : _subscribeAttributeNameSet) {
            try {
                subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.Injections." + attributeName));
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
    * Unsubscribes a federate from the Injections object class.
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
    * (that is, the Injections object class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the Injections object class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the Injections object class).
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
        else if (datamemberHandle == _Value_handle) return "Value";
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
                + "," + "Value:" + get_Value()
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
    * Publishes the "Value" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Value" attribute for publication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void publish_Value() {
        _publishAttributeNameSet.add( "Value" );
    }

    /**
    * Unpublishes the "Value" attribute of the attribute's containing object
    * class for a federate.
    * Note:  This method only marks the "Value" attribute for unpublication.
    * To actually publish the attribute, the federate must (re)publish its containing
    * object class.
    * (using &lt;objectClassName&gt;.publish( RTIambassador rti ) ).
    */
    public static void unpublish_Value() {
        _publishAttributeNameSet.remove( "Value" );
    }

    /**
    * Subscribes a federate to the "Value" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Value" attribute for subscription.
    * To actually subscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void subscribe_Value() {
        _subscribeAttributeNameSet.add( "Value" );
    }

    /**
    * Unsubscribes a federate from the "Value" attribute of the attribute's
    * containing object class.
    * Note:  This method only marks the "Value" attribute for unsubscription.
    * To actually unsubscribe to the attribute, the federate must (re)subscribe to its
    * containing object class.
    * (using &lt;objectClassName&gt;.subscribe( RTIambassador rti ) ).
    */
    public static void unsubscribe_Value() {
        _subscribeAttributeNameSet.remove( "Value" );
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

    protected Attribute< String > _Value =
            new Attribute< String >(  new String( "" )  );

    /**
    * Set the value of the "Value" attribute to "value" for this object.
    *
    * @param value the new value for the "Value" attribute
    */
    public void set_Value( String value ) {
        _Value.setValue( value );
        _Value.setTime( getTime() );
    }

    /**
    * Returns the value of the "Value" attribute of this object.
    *
    * @return the value of the "Value" attribute
    */
    public String get_Value() {
        return _Value.getValue();
    }

    /**
    * Returns the current timestamp of the "Value" attribute of this object.
    *
    * @return the current timestamp of the "Value" attribute
    */
    public double get_Value_time() {
        return _Value.getTime();
    }

    protected Injections( ReflectedAttributes datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    protected Injections( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setAttributes( datamemberMap );
    }

    /**
    * Creates an instance of the Injections object class, using
    * "datamemberMap" to initialize its attribute values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new Injections object class instance
    */
    public Injections( ReflectedAttributes datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #Injections( ReflectedAttributes datamemberMap )}, except this
    * new Injections object class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * attributes of this new Injections object class instance
    * @param logicalTime timestamp for this new Injections object class
    * instance
    */
    public Injections( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new Injections object class instance that is a duplicate
    * of the instance referred to by Injections_var.
    *
    * @param Injections_var Injections object class instance of which
    * this newly created Injections object class instance will be a
    * duplicate
    */
    public Injections( Injections Injections_var ) {
        super( Injections_var );

        set_Protocol( Injections_var.get_Protocol() );
        set_Value( Injections_var.get_Value() );
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
        else if ( "Value".equals(datamemberName) ) return get_Value();
        else return super.getAttribute( datamemberName );
    }

    protected boolean setAttributeAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "Protocol".equals( datamemberName) ) set_Protocol( val );
        else if ( "Value".equals( datamemberName) ) set_Value( val );
        else retval = super.setAttributeAux( datamemberName, val );

        return retval;
    }

    protected boolean setAttributeAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "Protocol".equals( datamemberName) ) set_Protocol( (String)val );
        else if ( "Value".equals( datamemberName) ) set_Value( (String)val );
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

        if (_publishAttributeNameSet.contains("Value") && _Value.shouldBeUpdated(force)) {
            datamembers.add( getAttributeHandle("Value"), getAttribute("Value").toString().getBytes() );
            _Value.setHasBeenUpdated();
        }

        return datamembers;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof Injections ) {
            Injections data = (Injections)object;
            _Protocol = data._Protocol;
            _Value = data._Value;
        }
    }
}


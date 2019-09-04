package org.webgme.guest.ucefgateway.rti;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;

import hla.rti.FederateNotExecutionMember;
import hla.rti.InteractionClassNotDefined;
import hla.rti.InteractionClassNotPublished;
import hla.rti.InteractionClassNotSubscribed;
import hla.rti.LogicalTime;
import hla.rti.NameNotFound;
import hla.rti.RTIambassador;
import hla.rti.ReceivedInteraction;

import org.cpswt.hla.*;

/**
* Implements InteractionRoot.C2WInteractionRoot.CAN
*/
public class CAN extends C2WInteractionRoot {

    private static final Logger logger = LogManager.getLogger();

    /**
    * Creates an instance of the CAN interaction class with default parameter values.
    */
    public CAN() {}

    private static int _ACKslot_handle;
    private static int _CRC_handle;
    private static int _DLC_handle;
    private static int _DataField_handle;
    private static int _EndOfFrame_handle;
    private static int _ID11B_handle;
    private static int _ID18B_handle;
    private static int _IDE_handle;
    private static int _Parameter_handle;
    private static int _RTR_handle;
    private static int _ReservedBit1_handle;
    private static int _ReservedBit2_handle;
    private static int _SRR_handle;
    private static int _StartOfFrame_handle;
    private static int _actualLogicalGenerationTime_handle;
    private static int _federateFilter_handle;
    private static int _originFed_handle;
    private static int _sourceFed_handle;

    private static boolean _isInitialized = false;

    private static int _handle;

    /**
    * Returns the handle (RTI assigned) of the CAN interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the handle of the class pertaining to the reference,
    * rather than the handle of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassHandle()}.
    *
    * @return the RTI assigned integer handle that represents this interaction class
    */
    public static int get_handle() {
        return _handle;
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of the CAN interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return the name of the class pertaining to the reference,
    * rather than the name of the class for the instance referred to by the reference.
    * For the polymorphic version of this method, use {@link #getClassName()}.
    *
    * @return the fully-qualified HLA class path for this interaction class
    */
    public static String get_class_name() {
        return "InteractionRoot.C2WInteractionRoot.CAN";
    }

    /**
    * Returns the simple name (the last name in the dot-delimited fully-qualified
    * class name) of the CAN interaction class.
    *
    * @return the name of this interaction class
    */
    public static String get_simple_class_name() {
        return "CAN";
    }

    private static Set< String > _datamemberNames = new HashSet< String >();
    private static Set< String > _allDatamemberNames = new HashSet< String >();

    /**
    * Returns a set containing the names of all of the non-hidden parameters in the
    * CAN interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getParameterNames()}.
    *
    * @return a modifiable set of the non-hidden parameter names for this interaction class
    */
    public static Set< String > get_parameter_names() {
        return new HashSet< String >(_datamemberNames);
    }

    /**
    * Returns a set containing the names of all of the parameters in the
    * CAN interaction class.
    * Note: As this is a static method, it is NOT polymorphic, and so, if called on
    * a reference will return a set of parameter names pertaining to the reference,
    * rather than the parameter names of the class for the instance referred to by
    * the reference.  For the polymorphic version of this method, use
    * {@link #getParameterNames()}.
    *
    * @return a modifiable set of the parameter names for this interaction class
    */
    public static Set< String > get_all_parameter_names() {
        return new HashSet< String >(_allDatamemberNames);
    }

    static {
        _classNameSet.add("InteractionRoot.C2WInteractionRoot.CAN");
        _classNameClassMap.put("InteractionRoot.C2WInteractionRoot.CAN", CAN.class);

        _datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.CAN", _datamemberNames);
        _allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.CAN", _allDatamemberNames);

        _datamemberNames.add("ACKslot");
        _datamemberNames.add("CRC");
        _datamemberNames.add("DLC");
        _datamemberNames.add("DataField");
        _datamemberNames.add("EndOfFrame");
        _datamemberNames.add("ID11B");
        _datamemberNames.add("ID18B");
        _datamemberNames.add("IDE");
        _datamemberNames.add("Parameter");
        _datamemberNames.add("RTR");
        _datamemberNames.add("ReservedBit1");
        _datamemberNames.add("ReservedBit2");
        _datamemberNames.add("SRR");
        _datamemberNames.add("StartOfFrame");

        _datamemberTypeMap.put("ACKslot", "String");
        _datamemberTypeMap.put("CRC", "String");
        _datamemberTypeMap.put("DLC", "String");
        _datamemberTypeMap.put("DataField", "String");
        _datamemberTypeMap.put("EndOfFrame", "String");
        _datamemberTypeMap.put("ID11B", "String");
        _datamemberTypeMap.put("ID18B", "String");
        _datamemberTypeMap.put("IDE", "String");
        _datamemberTypeMap.put("Parameter", "String");
        _datamemberTypeMap.put("RTR", "String");
        _datamemberTypeMap.put("ReservedBit1", "String");
        _datamemberTypeMap.put("ReservedBit2", "String");
        _datamemberTypeMap.put("SRR", "String");
        _datamemberTypeMap.put("StartOfFrame", "String");

        _allDatamemberNames.add("ACKslot");
        _allDatamemberNames.add("CRC");
        _allDatamemberNames.add("DLC");
        _allDatamemberNames.add("DataField");
        _allDatamemberNames.add("EndOfFrame");
        _allDatamemberNames.add("ID11B");
        _allDatamemberNames.add("ID18B");
        _allDatamemberNames.add("IDE");
        _allDatamemberNames.add("Parameter");
        _allDatamemberNames.add("RTR");
        _allDatamemberNames.add("ReservedBit1");
        _allDatamemberNames.add("ReservedBit2");
        _allDatamemberNames.add("SRR");
        _allDatamemberNames.add("StartOfFrame");
        _allDatamemberNames.add("actualLogicalGenerationTime");
        _allDatamemberNames.add("federateFilter");
        _allDatamemberNames.add("originFed");
        _allDatamemberNames.add("sourceFed");
    }

    protected static void init(RTIambassador rti) {
        if (_isInitialized) return;
        _isInitialized = true;

        C2WInteractionRoot.init(rti);

        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.CAN");
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

        _classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN", get_handle());
        _classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.CAN");
        _classHandleSimpleNameMap.put(get_handle(), "CAN");

        isNotInitialized = true;
        while(isNotInitialized) {
            try {
                _ACKslot_handle = rti.getParameterHandle("ACKslot", get_handle());
                _CRC_handle = rti.getParameterHandle("CRC", get_handle());
                _DLC_handle = rti.getParameterHandle("DLC", get_handle());
                _DataField_handle = rti.getParameterHandle("DataField", get_handle());
                _EndOfFrame_handle = rti.getParameterHandle("EndOfFrame", get_handle());
                _ID11B_handle = rti.getParameterHandle("ID11B", get_handle());
                _ID18B_handle = rti.getParameterHandle("ID18B", get_handle());
                _IDE_handle = rti.getParameterHandle("IDE", get_handle());
                _Parameter_handle = rti.getParameterHandle("Parameter", get_handle());
                _RTR_handle = rti.getParameterHandle("RTR", get_handle());
                _ReservedBit1_handle = rti.getParameterHandle("ReservedBit1", get_handle());
                _ReservedBit2_handle = rti.getParameterHandle("ReservedBit2", get_handle());
                _SRR_handle = rti.getParameterHandle("SRR", get_handle());
                _StartOfFrame_handle = rti.getParameterHandle("StartOfFrame", get_handle());
                _actualLogicalGenerationTime_handle = rti.getParameterHandle("actualLogicalGenerationTime", get_handle());
                _federateFilter_handle = rti.getParameterHandle("federateFilter", get_handle());
                _originFed_handle = rti.getParameterHandle("originFed", get_handle());
                _sourceFed_handle = rti.getParameterHandle("sourceFed", get_handle());
                isNotInitialized = false;
            } catch (FederateNotExecutionMember e) {
                logger.error("could not initialize: Federate Not Execution Member", e);
                return;
            } catch (InteractionClassNotDefined e) {
                logger.error("could not initialize: Interaction Class Not Defined", e);
                return;
            } catch (NameNotFound e) {
                logger.error("could not initialize: Name Not Found", e);
                return;
            } catch (Exception e) {
                logger.error(e);
                CpswtUtils.sleepDefault();
            }
        }

        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.ACKslot", _ACKslot_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.CRC", _CRC_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.DLC", _DLC_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.DataField", _DataField_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.EndOfFrame", _EndOfFrame_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.ID11B", _ID11B_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.ID18B", _ID18B_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.IDE", _IDE_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.Parameter", _Parameter_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.RTR", _RTR_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.ReservedBit1", _ReservedBit1_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.ReservedBit2", _ReservedBit2_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.SRR", _SRR_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.StartOfFrame", _StartOfFrame_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.actualLogicalGenerationTime", _actualLogicalGenerationTime_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.federateFilter", _federateFilter_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.originFed", _originFed_handle);
        _datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.CAN.sourceFed", _sourceFed_handle);

        _datamemberHandleNameMap.put(_ACKslot_handle, "ACKslot");
        _datamemberHandleNameMap.put(_CRC_handle, "CRC");
        _datamemberHandleNameMap.put(_DLC_handle, "DLC");
        _datamemberHandleNameMap.put(_DataField_handle, "DataField");
        _datamemberHandleNameMap.put(_EndOfFrame_handle, "EndOfFrame");
        _datamemberHandleNameMap.put(_ID11B_handle, "ID11B");
        _datamemberHandleNameMap.put(_ID18B_handle, "ID18B");
        _datamemberHandleNameMap.put(_IDE_handle, "IDE");
        _datamemberHandleNameMap.put(_Parameter_handle, "Parameter");
        _datamemberHandleNameMap.put(_RTR_handle, "RTR");
        _datamemberHandleNameMap.put(_ReservedBit1_handle, "ReservedBit1");
        _datamemberHandleNameMap.put(_ReservedBit2_handle, "ReservedBit2");
        _datamemberHandleNameMap.put(_SRR_handle, "SRR");
        _datamemberHandleNameMap.put(_StartOfFrame_handle, "StartOfFrame");
        _datamemberHandleNameMap.put(_actualLogicalGenerationTime_handle, "actualLogicalGenerationTime");
        _datamemberHandleNameMap.put(_federateFilter_handle, "federateFilter");
        _datamemberHandleNameMap.put(_originFed_handle, "originFed");
        _datamemberHandleNameMap.put(_sourceFed_handle, "sourceFed");
    }

    private static boolean _isPublished = false;

    /**
    * Publishes the CAN interaction class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void publish(RTIambassador rti) {
        if (_isPublished) return;

        init(rti);

        synchronized(rti) {
            boolean isNotPublished = true;
            while(isNotPublished) {
                try {
                    rti.publishInteractionClass(get_handle());
                    isNotPublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not publish: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not publish: Interaction Class Not Defined", e);
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
    * Unpublishes the CAN interaction class for a federate.
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
                    rti.unpublishInteractionClass(get_handle());
                    isNotUnpublished = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unpublish: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not unpublish: Interaction Class Not Defined", e);
                    return;
                } catch (InteractionClassNotPublished e) {
                    logger.error("could not unpublish: Interaction Class Not Published", e);
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
    * Subscribes a federate to the CAN interaction class.
    *
    * @param rti handle to the Local RTI Component
    */
    public static void subscribe(RTIambassador rti) {
        if (_isSubscribed) return;

        init(rti);

        synchronized(rti) {
            boolean isNotSubscribed = true;
            while(isNotSubscribed) {
                try {
                    rti.subscribeInteractionClass(get_handle());
                    isNotSubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not subscribe: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not subscribe: Interaction Class Not Defined", e);
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
    * Unsubscribes a federate from the CAN interaction class.
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
                    rti.unsubscribeInteractionClass(get_handle());
                    isNotUnsubscribed = false;
                } catch (FederateNotExecutionMember e) {
                    logger.error("could not unsubscribe: Federate Not Execution Member", e);
                    return;
                } catch (InteractionClassNotDefined e) {
                    logger.error("could not unsubscribe: Interaction Class Not Defined", e);
                    return;
                } catch (InteractionClassNotSubscribed e) {
                    logger.error("could not unsubscribe: Interaction Class Not Subscribed", e);
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
    * (that is, the CAN interaction class).
    *
    * @param handle handle to compare to the value of the handle (RTI assigned) of
    * this class (the CAN interaction class).
    * @return "true" if "handle" matches the value of the handle of this class
    * (that is, the CAN interaction class).
    */
    public static boolean match(int handle) {
        return handle == get_handle();
    }

    /**
    * Returns the handle (RTI assigned) of this instance's interaction class .
    *
    * @return the handle (RTI assigned) if this instance's interaction class
    */
    public int getClassHandle() {
        return get_handle();
    }

    /**
    * Returns the fully-qualified (dot-delimited) name of this instance's interaction class.
    *
    * @return the fully-qualified (dot-delimited) name of this instance's interaction class
    */
    public String getClassName() {
        return get_class_name();
    }

    /**
    * Returns the simple name (last name in its fully-qualified dot-delimited name)
    * of this instance's interaction class.
    *
    * @return the simple name of this instance's interaction class
    */
    public String getSimpleClassName() {
        return get_simple_class_name();
    }

    /**
    * Returns a set containing the names of all of the non-hiddenparameters of an
    * interaction class instance.
    *
    * @return set containing the names of all of the parameters of an
    * interaction class instance
    */
    public Set< String > getParameterNames() {
        return get_parameter_names();
    }

    /**
    * Returns a set containing the names of all of the parameters of an
    * interaction class instance.
    *
    * @return set containing the names of all of the parameters of an
    * interaction class instance
    */
    public Set< String > getAllParameterNames() {
        return get_all_parameter_names();
    }

    @Override
    public String getParameterName(int datamemberHandle) {
        if (datamemberHandle == _ACKslot_handle) return "ACKslot";
        else if (datamemberHandle == _CRC_handle) return "CRC";
        else if (datamemberHandle == _DLC_handle) return "DLC";
        else if (datamemberHandle == _DataField_handle) return "DataField";
        else if (datamemberHandle == _EndOfFrame_handle) return "EndOfFrame";
        else if (datamemberHandle == _ID11B_handle) return "ID11B";
        else if (datamemberHandle == _ID18B_handle) return "ID18B";
        else if (datamemberHandle == _IDE_handle) return "IDE";
        else if (datamemberHandle == _Parameter_handle) return "Parameter";
        else if (datamemberHandle == _RTR_handle) return "RTR";
        else if (datamemberHandle == _ReservedBit1_handle) return "ReservedBit1";
        else if (datamemberHandle == _ReservedBit2_handle) return "ReservedBit2";
        else if (datamemberHandle == _SRR_handle) return "SRR";
        else if (datamemberHandle == _StartOfFrame_handle) return "StartOfFrame";
        else if (datamemberHandle == _actualLogicalGenerationTime_handle) return "actualLogicalGenerationTime";
        else if (datamemberHandle == _federateFilter_handle) return "federateFilter";
        else if (datamemberHandle == _originFed_handle) return "originFed";
        else if (datamemberHandle == _sourceFed_handle) return "sourceFed";
        else return super.getParameterName(datamemberHandle);
    }

    /**
    * Publishes the interaction class of this instance of the class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void publishInteraction(RTIambassador rti) {
        publish(rti);
    }

    /**
    * Unpublishes the interaction class of this instance of this class for a federate.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unpublishInteraction(RTIambassador rti) {
        unpublish(rti);
    }

    /**
    * Subscribes a federate to the interaction class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void subscribeInteraction(RTIambassador rti) {
        subscribe(rti);
    }

    /**
    * Unsubscribes a federate from the interaction class of this instance of this class.
    *
    * @param rti handle to the Local RTI Component
    */
    public void unsubscribeInteraction(RTIambassador rti) {
        unsubscribe(rti);
    }

    @Override
    public String toString() {
        return getClass().getName() + "("
                + "ACKslot:" + get_ACKslot()
                + "," + "CRC:" + get_CRC()
                + "," + "DLC:" + get_DLC()
                + "," + "DataField:" + get_DataField()
                + "," + "EndOfFrame:" + get_EndOfFrame()
                + "," + "ID11B:" + get_ID11B()
                + "," + "ID18B:" + get_ID18B()
                + "," + "IDE:" + get_IDE()
                + "," + "Parameter:" + get_Parameter()
                + "," + "RTR:" + get_RTR()
                + "," + "ReservedBit1:" + get_ReservedBit1()
                + "," + "ReservedBit2:" + get_ReservedBit2()
                + "," + "SRR:" + get_SRR()
                + "," + "StartOfFrame:" + get_StartOfFrame()
                + "," + "actualLogicalGenerationTime:" + get_actualLogicalGenerationTime()
                + "," + "federateFilter:" + get_federateFilter()
                + "," + "originFed:" + get_originFed()
                + "," + "sourceFed:" + get_sourceFed()
                + ")";
    }

    private String _ACKslot = "";
    private String _CRC = "";
    private String _DLC = "";
    private String _DataField = "";
    private String _EndOfFrame = "";
    private String _ID11B = "";
    private String _ID18B = "";
    private String _IDE = "";
    private String _Parameter = "";
    private String _RTR = "";
    private String _ReservedBit1 = "";
    private String _ReservedBit2 = "";
    private String _SRR = "";
    private String _StartOfFrame = "";

    /**
    * Set the value of the "ACKslot" parameter to "value" for this parameter.
    *
    * @param value the new value for the "ACKslot" parameter
    */
    public void set_ACKslot( String value ) {
        _ACKslot = value;
    }

    /**
    * Returns the value of the "ACKslot" parameter of this interaction.
    *
    * @return the value of the "ACKslot" parameter
    */
    public String get_ACKslot() {
        return _ACKslot;
    }
    /**
    * Set the value of the "CRC" parameter to "value" for this parameter.
    *
    * @param value the new value for the "CRC" parameter
    */
    public void set_CRC( String value ) {
        _CRC = value;
    }

    /**
    * Returns the value of the "CRC" parameter of this interaction.
    *
    * @return the value of the "CRC" parameter
    */
    public String get_CRC() {
        return _CRC;
    }
    /**
    * Set the value of the "DLC" parameter to "value" for this parameter.
    *
    * @param value the new value for the "DLC" parameter
    */
    public void set_DLC( String value ) {
        _DLC = value;
    }

    /**
    * Returns the value of the "DLC" parameter of this interaction.
    *
    * @return the value of the "DLC" parameter
    */
    public String get_DLC() {
        return _DLC;
    }
    /**
    * Set the value of the "DataField" parameter to "value" for this parameter.
    *
    * @param value the new value for the "DataField" parameter
    */
    public void set_DataField( String value ) {
        _DataField = value;
    }

    /**
    * Returns the value of the "DataField" parameter of this interaction.
    *
    * @return the value of the "DataField" parameter
    */
    public String get_DataField() {
        return _DataField;
    }
    /**
    * Set the value of the "EndOfFrame" parameter to "value" for this parameter.
    *
    * @param value the new value for the "EndOfFrame" parameter
    */
    public void set_EndOfFrame( String value ) {
        _EndOfFrame = value;
    }

    /**
    * Returns the value of the "EndOfFrame" parameter of this interaction.
    *
    * @return the value of the "EndOfFrame" parameter
    */
    public String get_EndOfFrame() {
        return _EndOfFrame;
    }
    /**
    * Set the value of the "ID11B" parameter to "value" for this parameter.
    *
    * @param value the new value for the "ID11B" parameter
    */
    public void set_ID11B( String value ) {
        _ID11B = value;
    }

    /**
    * Returns the value of the "ID11B" parameter of this interaction.
    *
    * @return the value of the "ID11B" parameter
    */
    public String get_ID11B() {
        return _ID11B;
    }
    /**
    * Set the value of the "ID18B" parameter to "value" for this parameter.
    *
    * @param value the new value for the "ID18B" parameter
    */
    public void set_ID18B( String value ) {
        _ID18B = value;
    }

    /**
    * Returns the value of the "ID18B" parameter of this interaction.
    *
    * @return the value of the "ID18B" parameter
    */
    public String get_ID18B() {
        return _ID18B;
    }
    /**
    * Set the value of the "IDE" parameter to "value" for this parameter.
    *
    * @param value the new value for the "IDE" parameter
    */
    public void set_IDE( String value ) {
        _IDE = value;
    }

    /**
    * Returns the value of the "IDE" parameter of this interaction.
    *
    * @return the value of the "IDE" parameter
    */
    public String get_IDE() {
        return _IDE;
    }
    /**
    * Set the value of the "Parameter" parameter to "value" for this parameter.
    *
    * @param value the new value for the "Parameter" parameter
    */
    public void set_Parameter( String value ) {
        _Parameter = value;
    }

    /**
    * Returns the value of the "Parameter" parameter of this interaction.
    *
    * @return the value of the "Parameter" parameter
    */
    public String get_Parameter() {
        return _Parameter;
    }
    /**
    * Set the value of the "RTR" parameter to "value" for this parameter.
    *
    * @param value the new value for the "RTR" parameter
    */
    public void set_RTR( String value ) {
        _RTR = value;
    }

    /**
    * Returns the value of the "RTR" parameter of this interaction.
    *
    * @return the value of the "RTR" parameter
    */
    public String get_RTR() {
        return _RTR;
    }
    /**
    * Set the value of the "ReservedBit1" parameter to "value" for this parameter.
    *
    * @param value the new value for the "ReservedBit1" parameter
    */
    public void set_ReservedBit1( String value ) {
        _ReservedBit1 = value;
    }

    /**
    * Returns the value of the "ReservedBit1" parameter of this interaction.
    *
    * @return the value of the "ReservedBit1" parameter
    */
    public String get_ReservedBit1() {
        return _ReservedBit1;
    }
    /**
    * Set the value of the "ReservedBit2" parameter to "value" for this parameter.
    *
    * @param value the new value for the "ReservedBit2" parameter
    */
    public void set_ReservedBit2( String value ) {
        _ReservedBit2 = value;
    }

    /**
    * Returns the value of the "ReservedBit2" parameter of this interaction.
    *
    * @return the value of the "ReservedBit2" parameter
    */
    public String get_ReservedBit2() {
        return _ReservedBit2;
    }
    /**
    * Set the value of the "SRR" parameter to "value" for this parameter.
    *
    * @param value the new value for the "SRR" parameter
    */
    public void set_SRR( String value ) {
        _SRR = value;
    }

    /**
    * Returns the value of the "SRR" parameter of this interaction.
    *
    * @return the value of the "SRR" parameter
    */
    public String get_SRR() {
        return _SRR;
    }
    /**
    * Set the value of the "StartOfFrame" parameter to "value" for this parameter.
    *
    * @param value the new value for the "StartOfFrame" parameter
    */
    public void set_StartOfFrame( String value ) {
        _StartOfFrame = value;
    }

    /**
    * Returns the value of the "StartOfFrame" parameter of this interaction.
    *
    * @return the value of the "StartOfFrame" parameter
    */
    public String get_StartOfFrame() {
        return _StartOfFrame;
    }

    protected CAN( ReceivedInteraction datamemberMap, boolean initFlag ) {
        super( datamemberMap, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    protected CAN( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
        super( datamemberMap, logicalTime, false );
        if ( initFlag ) setParameters( datamemberMap );
    }

    /**
    * Creates an instance of the CAN interaction class, using
    * "datamemberMap" to initialize its parameter values.
    * "datamemberMap" is usually acquired as an argument to an RTI federate
    * callback method, such as "receiveInteraction".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new CAN interaction class instance
    */
    public CAN( ReceivedInteraction datamemberMap ) {
        this( datamemberMap, true );
    }

    /**
    * Like {@link #CAN( ReceivedInteraction datamemberMap )}, except this
    * new CAN interaction class instance is given a timestamp of
    * "logicalTime".
    *
    * @param datamemberMap data structure containing initial values for the
    * parameters of this new CAN interaction class instance
    * @param logicalTime timestamp for this new CAN interaction class
    * instance
    */
    public CAN( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
        this( datamemberMap, logicalTime, true );
    }

    /**
    * Creates a new CAN interaction class instance that is a duplicate
    * of the instance referred to by CAN_var.
    *
    * @param CAN_var CAN interaction class instance of which
    * this newly created CAN interaction class instance will be a
    * duplicate
    */
    public CAN( CAN CAN_var ) {
        super( CAN_var );

        set_ACKslot( CAN_var.get_ACKslot() );
        set_CRC( CAN_var.get_CRC() );
        set_DLC( CAN_var.get_DLC() );
        set_DataField( CAN_var.get_DataField() );
        set_EndOfFrame( CAN_var.get_EndOfFrame() );
        set_ID11B( CAN_var.get_ID11B() );
        set_ID18B( CAN_var.get_ID18B() );
        set_IDE( CAN_var.get_IDE() );
        set_Parameter( CAN_var.get_Parameter() );
        set_RTR( CAN_var.get_RTR() );
        set_ReservedBit1( CAN_var.get_ReservedBit1() );
        set_ReservedBit2( CAN_var.get_ReservedBit2() );
        set_SRR( CAN_var.get_SRR() );
        set_StartOfFrame( CAN_var.get_StartOfFrame() );
    }

    /**
    * Returns the value of the parameter whose name is "datamemberName"
    * for this interaction.
    *
    * @param datamemberName name of parameter whose value is to be
    * returned
    * @return value of the parameter whose name is "datamemberName"
    * for this interaction
    */
    public Object getParameter( String datamemberName ) {
        if ( "ACKslot".equals(datamemberName) ) return get_ACKslot();
        else if ( "CRC".equals(datamemberName) ) return get_CRC();
        else if ( "DLC".equals(datamemberName) ) return get_DLC();
        else if ( "DataField".equals(datamemberName) ) return get_DataField();
        else if ( "EndOfFrame".equals(datamemberName) ) return get_EndOfFrame();
        else if ( "ID11B".equals(datamemberName) ) return get_ID11B();
        else if ( "ID18B".equals(datamemberName) ) return get_ID18B();
        else if ( "IDE".equals(datamemberName) ) return get_IDE();
        else if ( "Parameter".equals(datamemberName) ) return get_Parameter();
        else if ( "RTR".equals(datamemberName) ) return get_RTR();
        else if ( "ReservedBit1".equals(datamemberName) ) return get_ReservedBit1();
        else if ( "ReservedBit2".equals(datamemberName) ) return get_ReservedBit2();
        else if ( "SRR".equals(datamemberName) ) return get_SRR();
        else if ( "StartOfFrame".equals(datamemberName) ) return get_StartOfFrame();
        else return super.getParameter( datamemberName );
    }

    protected boolean setParameterAux( String datamemberName, String val ) {
        boolean retval = true;
        if ( "ACKslot".equals( datamemberName) ) set_ACKslot( val );
        else if ( "CRC".equals( datamemberName) ) set_CRC( val );
        else if ( "DLC".equals( datamemberName) ) set_DLC( val );
        else if ( "DataField".equals( datamemberName) ) set_DataField( val );
        else if ( "EndOfFrame".equals( datamemberName) ) set_EndOfFrame( val );
        else if ( "ID11B".equals( datamemberName) ) set_ID11B( val );
        else if ( "ID18B".equals( datamemberName) ) set_ID18B( val );
        else if ( "IDE".equals( datamemberName) ) set_IDE( val );
        else if ( "Parameter".equals( datamemberName) ) set_Parameter( val );
        else if ( "RTR".equals( datamemberName) ) set_RTR( val );
        else if ( "ReservedBit1".equals( datamemberName) ) set_ReservedBit1( val );
        else if ( "ReservedBit2".equals( datamemberName) ) set_ReservedBit2( val );
        else if ( "SRR".equals( datamemberName) ) set_SRR( val );
        else if ( "StartOfFrame".equals( datamemberName) ) set_StartOfFrame( val );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    protected boolean setParameterAux( String datamemberName, Object val ) {
        boolean retval = true;
        if ( "ACKslot".equals( datamemberName) ) set_ACKslot( (String)val );
        else if ( "CRC".equals( datamemberName) ) set_CRC( (String)val );
        else if ( "DLC".equals( datamemberName) ) set_DLC( (String)val );
        else if ( "DataField".equals( datamemberName) ) set_DataField( (String)val );
        else if ( "EndOfFrame".equals( datamemberName) ) set_EndOfFrame( (String)val );
        else if ( "ID11B".equals( datamemberName) ) set_ID11B( (String)val );
        else if ( "ID18B".equals( datamemberName) ) set_ID18B( (String)val );
        else if ( "IDE".equals( datamemberName) ) set_IDE( (String)val );
        else if ( "Parameter".equals( datamemberName) ) set_Parameter( (String)val );
        else if ( "RTR".equals( datamemberName) ) set_RTR( (String)val );
        else if ( "ReservedBit1".equals( datamemberName) ) set_ReservedBit1( (String)val );
        else if ( "ReservedBit2".equals( datamemberName) ) set_ReservedBit2( (String)val );
        else if ( "SRR".equals( datamemberName) ) set_SRR( (String)val );
        else if ( "StartOfFrame".equals( datamemberName) ) set_StartOfFrame( (String)val );
        else retval = super.setParameterAux( datamemberName, val );

        return retval;
    }

    public void copyFrom( Object object ) {
        super.copyFrom( object );
        if ( object instanceof CAN ) {
            CAN data = (CAN)object;
            _ACKslot = data._ACKslot;
            _CRC = data._CRC;
            _DLC = data._DLC;
            _DataField = data._DataField;
            _EndOfFrame = data._EndOfFrame;
            _ID11B = data._ID11B;
            _ID18B = data._ID18B;
            _IDE = data._IDE;
            _Parameter = data._Parameter;
            _RTR = data._RTR;
            _ReservedBit1 = data._ReservedBit1;
            _ReservedBit2 = data._ReservedBit2;
            _SRR = data._SRR;
            _StartOfFrame = data._StartOfFrame;
        }
    }
}


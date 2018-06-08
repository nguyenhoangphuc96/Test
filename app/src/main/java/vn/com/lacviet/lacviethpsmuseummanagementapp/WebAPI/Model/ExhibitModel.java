package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExhibitModel {

    @SerializedName("NO")
    @Expose
    private Integer nO;
    @SerializedName("EXHID")
    @Expose
    private Integer eXHID;
    @SerializedName("EXHIBITNAME")
    @Expose
    private String eXHIBITNAME;
    @SerializedName("IMAGE")
    @Expose
    private Object iMAGE;
    @SerializedName("OTHERNAME")
    @Expose
    private String oTHERNAME;
    @SerializedName("CODEID")
    @Expose
    private String cODEID;
    @SerializedName("NUMBER")
    @Expose
    private Integer nUMBER;
    @SerializedName("ELEMENT")
    @Expose
    private String eLEMENT;
    @SerializedName("MATERIALTYPE")
    @Expose
    private Integer mATERIALTYPE;
    @SerializedName("MATERIALNAME")
    @Expose
    private String mATERIALNAME;
    @SerializedName("VERIFICATIONDATE")
    @Expose
    private String vERIFICATIONDATE;
    @SerializedName("AGES")
    @Expose
    private String aGES;
    @SerializedName("EXPERTISE")
    @Expose
    private Integer eXPERTISE;
    @SerializedName("EXPERTISENAME")
    @Expose
    private String eXPERTISENAME;
    @SerializedName("EXPERTISEDATE")
    @Expose
    private String eXPERTISEDATE;
    @SerializedName("SOURCE")
    @Expose
    private Integer sOURCE;
    @SerializedName("SOURCENAME")
    @Expose
    private String sOURCENAME;
    @SerializedName("RESOURCENOTE")
    @Expose
    private String rESOURCENOTE;
    @SerializedName("TIME")
    @Expose
    private Integer tIME;
    @SerializedName("TIMENAME")
    @Expose
    private String tIMENAME;
    @SerializedName("TIMENOTE")
    @Expose
    private String tIMENOTE;
    @SerializedName("LOCATIONID")
    @Expose
    private String lOCATIONID;
    @SerializedName("LOCATIONNAME")
    @Expose
    private String lOCATIONNAME;
    @SerializedName("LOCATIONNOTE")
    @Expose
    private String lOCATIONNOTE;
    @SerializedName("OWNER")
    @Expose
    private Integer oWNER;
    @SerializedName("OWNERNAME")
    @Expose
    private String oWNERNAME;
    @SerializedName("OWNERADDRESS")
    @Expose
    private String oWNERADDRESS;
    @SerializedName("SECRETLEVEL")
    @Expose
    private Integer sECRETLEVEL;
    @SerializedName("SECRETLEVELNAME")
    @Expose
    private String sECRETLEVELNAME;
    @SerializedName("STUFF")
    @Expose
    private Integer sTUFF;
    @SerializedName("STUFFNAME")
    @Expose
    private String sTUFFNAME;
    @SerializedName("DESCPHYSICS")
    @Expose
    private String dESCPHYSICS;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("COLOR")
    @Expose
    private String cOLOR;
    @SerializedName("MAKETECH")
    @Expose
    private Integer mAKETECH;
    @SerializedName("MAKETECHNAME")
    @Expose
    private String mAKETECHNAME;
    @SerializedName("MAINTENANCE")
    @Expose
    private Integer mAINTENANCE;
    @SerializedName("MAINTENANCENAME")
    @Expose
    private String mAINTENANCENAME;
    @SerializedName("RELICS")
    @Expose
    private String rELICS;
    @SerializedName("VENDOR")
    @Expose
    private String vENDOR;
    @SerializedName("COST")
    @Expose
    private Integer cOST;
    @SerializedName("VENDORSADDRESS")
    @Expose
    private String vENDORSADDRESS;
    @SerializedName("CONTENT")
    @Expose
    private String cONTENT;
    @SerializedName("FIELD")
    @Expose
    private Integer fIELD;
    @SerializedName("FIELDNAME")
    @Expose
    private String fIELDNAME;
    @SerializedName("COLLECTOR")
    @Expose
    private Integer cOLLECTOR;
    @SerializedName("COLLECTORNAME")
    @Expose
    private String cOLLECTORNAME;
    @SerializedName("COLLECTORDATE")
    @Expose
    private String cOLLECTORDATE;
    @SerializedName("USERID")
    @Expose
    private String uSERID;
    @SerializedName("USERIDMODIFIED")
    @Expose
    private String uSERIDMODIFIED;
    @SerializedName("CREATEDDATE")
    @Expose
    private String cREATEDDATE;
    @SerializedName("MODIFIEDDATE")
    @Expose
    private String mODIFIEDDATE;
    @SerializedName("ISDELETE")
    @Expose
    private Object iSDELETE;
    @SerializedName("CUSTOMERID")
    @Expose
    private Object cUSTOMERID;
    @SerializedName("REALNUMBER")
    @Expose
    private Integer rEALNUMBER;
    @SerializedName("CHECKOUTs")
    @Expose
    private List<Object> cHECKOUTs = null;
    @SerializedName("COLLECTIONDETAILs")
    @Expose
    private List<Object> cOLLECTIONDETAILs = null;
    @SerializedName("COLLECTOR1")
    @Expose
    private Object cOLLECTOR1;
    @SerializedName("EXHIBITIMAGES")
    @Expose
    private List<Object> eXHIBITIMAGES = null;
    @SerializedName("EXPERTISE1")
    @Expose
    private Object eXPERTISE1;
    @SerializedName("FIELD1")
    @Expose
    private Object fIELD1;
    @SerializedName("LOCATION")
    @Expose
    private Object lOCATION;
    @SerializedName("MAINTENANCE1")
    @Expose
    private Object mAINTENANCE1;
    @SerializedName("MAKETECH1")
    @Expose
    private Object mAKETECH1;
    @SerializedName("MATERIALTYPE1")
    @Expose
    private Object mATERIALTYPE1;
    @SerializedName("OWNER1")
    @Expose
    private Object oWNER1;
    @SerializedName("SECRETLEVEL1")
    @Expose
    private Object sECRETLEVEL1;
    @SerializedName("SOURCE1")
    @Expose
    private Object sOURCE1;
    @SerializedName("STUFF1")
    @Expose
    private Object sTUFF1;
    @SerializedName("TIME1")
    @Expose
    private Object tIME1;

    public Integer getNO() {
        return nO;
    }

    public void setNO(Integer nO) {
        this.nO = nO;
    }

    public Integer getEXHID() {
        return eXHID;
    }

    public void setEXHID(Integer eXHID) {
        this.eXHID = eXHID;
    }

    public String getEXHIBITNAME() {
        return eXHIBITNAME;
    }

    public void setEXHIBITNAME(String eXHIBITNAME) {
        this.eXHIBITNAME = eXHIBITNAME;
    }

    public Object getIMAGE() {
        return iMAGE;
    }

    public void setIMAGE(Object iMAGE) {
        this.iMAGE = iMAGE;
    }

    public String getOTHERNAME() {
        return oTHERNAME;
    }

    public void setOTHERNAME(String oTHERNAME) {
        this.oTHERNAME = oTHERNAME;
    }

    public String getCODEID() {
        return cODEID;
    }

    public void setCODEID(String cODEID) {
        this.cODEID = cODEID;
    }

    public Integer getNUMBER() {
        return nUMBER;
    }

    public void setNUMBER(Integer nUMBER) {
        this.nUMBER = nUMBER;
    }

    public String getELEMENT() {
        return eLEMENT;
    }

    public void setELEMENT(String eLEMENT) {
        this.eLEMENT = eLEMENT;
    }

    public Integer getMATERIALTYPE() {
        return mATERIALTYPE;
    }

    public void setMATERIALTYPE(Integer mATERIALTYPE) {
        this.mATERIALTYPE = mATERIALTYPE;
    }

    public String getMATERIALNAME() {
        return mATERIALNAME;
    }

    public void setMATERIALNAME(String mATERIALNAME) {
        this.mATERIALNAME = mATERIALNAME;
    }

    public String getVERIFICATIONDATE() {
        return vERIFICATIONDATE;
    }

    public void setVERIFICATIONDATE(String vERIFICATIONDATE) {
        this.vERIFICATIONDATE = vERIFICATIONDATE;
    }

    public String getAGES() {
        return aGES;
    }

    public void setAGES(String aGES) {
        this.aGES = aGES;
    }

    public Integer getEXPERTISE() {
        return eXPERTISE;
    }

    public void setEXPERTISE(Integer eXPERTISE) {
        this.eXPERTISE = eXPERTISE;
    }

    public String getEXPERTISENAME() {
        return eXPERTISENAME;
    }

    public void setEXPERTISENAME(String eXPERTISENAME) {
        this.eXPERTISENAME = eXPERTISENAME;
    }

    public String getEXPERTISEDATE() {
        return eXPERTISEDATE;
    }

    public void setEXPERTISEDATE(String eXPERTISEDATE) {
        this.eXPERTISEDATE = eXPERTISEDATE;
    }

    public Integer getSOURCE() {
        return sOURCE;
    }

    public void setSOURCE(Integer sOURCE) {
        this.sOURCE = sOURCE;
    }

    public String getSOURCENAME() {
        return sOURCENAME;
    }

    public void setSOURCENAME(String sOURCENAME) {
        this.sOURCENAME = sOURCENAME;
    }

    public String getRESOURCENOTE() {
        return rESOURCENOTE;
    }

    public void setRESOURCENOTE(String rESOURCENOTE) {
        this.rESOURCENOTE = rESOURCENOTE;
    }

    public Integer getTIME() {
        return tIME;
    }

    public void setTIME(Integer tIME) {
        this.tIME = tIME;
    }

    public String getTIMENAME() {
        return tIMENAME;
    }

    public void setTIMENAME(String tIMENAME) {
        this.tIMENAME = tIMENAME;
    }

    public String getTIMENOTE() {
        return tIMENOTE;
    }

    public void setTIMENOTE(String tIMENOTE) {
        this.tIMENOTE = tIMENOTE;
    }

    public String getLOCATIONID() {
        return lOCATIONID;
    }

    public void setLOCATIONID(String lOCATIONID) {
        this.lOCATIONID = lOCATIONID;
    }

    public String getLOCATIONNAME() {
        return lOCATIONNAME;
    }

    public void setLOCATIONNAME(String lOCATIONNAME) {
        this.lOCATIONNAME = lOCATIONNAME;
    }

    public String getLOCATIONNOTE() {
        return lOCATIONNOTE;
    }

    public void setLOCATIONNOTE(String lOCATIONNOTE) {
        this.lOCATIONNOTE = lOCATIONNOTE;
    }

    public Integer getOWNER() {
        return oWNER;
    }

    public void setOWNER(Integer oWNER) {
        this.oWNER = oWNER;
    }

    public String getOWNERNAME() {
        return oWNERNAME;
    }

    public void setOWNERNAME(String oWNERNAME) {
        this.oWNERNAME = oWNERNAME;
    }

    public String getOWNERADDRESS() {
        return oWNERADDRESS;
    }

    public void setOWNERADDRESS(String oWNERADDRESS) {
        this.oWNERADDRESS = oWNERADDRESS;
    }

    public Integer getSECRETLEVEL() {
        return sECRETLEVEL;
    }

    public void setSECRETLEVEL(Integer sECRETLEVEL) {
        this.sECRETLEVEL = sECRETLEVEL;
    }

    public String getSECRETLEVELNAME() {
        return sECRETLEVELNAME;
    }

    public void setSECRETLEVELNAME(String sECRETLEVELNAME) {
        this.sECRETLEVELNAME = sECRETLEVELNAME;
    }

    public Integer getSTUFF() {
        return sTUFF;
    }

    public void setSTUFF(Integer sTUFF) {
        this.sTUFF = sTUFF;
    }

    public String getSTUFFNAME() {
        return sTUFFNAME;
    }

    public void setSTUFFNAME(String sTUFFNAME) {
        this.sTUFFNAME = sTUFFNAME;
    }

    public String getDESCPHYSICS() {
        return dESCPHYSICS;
    }

    public void setDESCPHYSICS(String dESCPHYSICS) {
        this.dESCPHYSICS = dESCPHYSICS;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getCOLOR() {
        return cOLOR;
    }

    public void setCOLOR(String cOLOR) {
        this.cOLOR = cOLOR;
    }

    public Integer getMAKETECH() {
        return mAKETECH;
    }

    public void setMAKETECH(Integer mAKETECH) {
        this.mAKETECH = mAKETECH;
    }

    public String getMAKETECHNAME() {
        return mAKETECHNAME;
    }

    public void setMAKETECHNAME(String mAKETECHNAME) {
        this.mAKETECHNAME = mAKETECHNAME;
    }

    public Integer getMAINTENANCE() {
        return mAINTENANCE;
    }

    public void setMAINTENANCE(Integer mAINTENANCE) {
        this.mAINTENANCE = mAINTENANCE;
    }

    public String getMAINTENANCENAME() {
        return mAINTENANCENAME;
    }

    public void setMAINTENANCENAME(String mAINTENANCENAME) {
        this.mAINTENANCENAME = mAINTENANCENAME;
    }

    public String getRELICS() {
        return rELICS;
    }

    public void setRELICS(String rELICS) {
        this.rELICS = rELICS;
    }

    public String getVENDOR() {
        return vENDOR;
    }

    public void setVENDOR(String vENDOR) {
        this.vENDOR = vENDOR;
    }

    public Integer getCOST() {
        return cOST;
    }

    public void setCOST(Integer cOST) {
        this.cOST = cOST;
    }

    public String getVENDORSADDRESS() {
        return vENDORSADDRESS;
    }

    public void setVENDORSADDRESS(String vENDORSADDRESS) {
        this.vENDORSADDRESS = vENDORSADDRESS;
    }

    public String getCONTENT() {
        return cONTENT;
    }

    public void setCONTENT(String cONTENT) {
        this.cONTENT = cONTENT;
    }

    public Integer getFIELD() {
        return fIELD;
    }

    public void setFIELD(Integer fIELD) {
        this.fIELD = fIELD;
    }

    public String getFIELDNAME() {
        return fIELDNAME;
    }

    public void setFIELDNAME(String fIELDNAME) {
        this.fIELDNAME = fIELDNAME;
    }

    public Integer getCOLLECTOR() {
        return cOLLECTOR;
    }

    public void setCOLLECTOR(Integer cOLLECTOR) {
        this.cOLLECTOR = cOLLECTOR;
    }

    public String getCOLLECTORNAME() {
        return cOLLECTORNAME;
    }

    public void setCOLLECTORNAME(String cOLLECTORNAME) {
        this.cOLLECTORNAME = cOLLECTORNAME;
    }

    public String getCOLLECTORDATE() {
        return cOLLECTORDATE;
    }

    public void setCOLLECTORDATE(String cOLLECTORDATE) {
        this.cOLLECTORDATE = cOLLECTORDATE;
    }

    public String getUSERID() {
        return uSERID;
    }

    public void setUSERID(String uSERID) {
        this.uSERID = uSERID;
    }

    public String getUSERIDMODIFIED() {
        return uSERIDMODIFIED;
    }

    public void setUSERIDMODIFIED(String uSERIDMODIFIED) {
        this.uSERIDMODIFIED = uSERIDMODIFIED;
    }

    public String getCREATEDDATE() {
        return cREATEDDATE;
    }

    public void setCREATEDDATE(String cREATEDDATE) {
        this.cREATEDDATE = cREATEDDATE;
    }

    public String getMODIFIEDDATE() {
        return mODIFIEDDATE;
    }

    public void setMODIFIEDDATE(String mODIFIEDDATE) {
        this.mODIFIEDDATE = mODIFIEDDATE;
    }

    public Object getISDELETE() {
        return iSDELETE;
    }

    public void setISDELETE(Object iSDELETE) {
        this.iSDELETE = iSDELETE;
    }

    public Object getCUSTOMERID() {
        return cUSTOMERID;
    }

    public void setCUSTOMERID(Object cUSTOMERID) {
        this.cUSTOMERID = cUSTOMERID;
    }

    public Integer getREALNUMBER() {
        return rEALNUMBER;
    }

    public void setREALNUMBER(Integer rEALNUMBER) {
        this.rEALNUMBER = rEALNUMBER;
    }

    public List<Object> getCHECKOUTs() {
        return cHECKOUTs;
    }

    public void setCHECKOUTs(List<Object> cHECKOUTs) {
        this.cHECKOUTs = cHECKOUTs;
    }

    public List<Object> getCOLLECTIONDETAILs() {
        return cOLLECTIONDETAILs;
    }

    public void setCOLLECTIONDETAILs(List<Object> cOLLECTIONDETAILs) {
        this.cOLLECTIONDETAILs = cOLLECTIONDETAILs;
    }

    public Object getCOLLECTOR1() {
        return cOLLECTOR1;
    }

    public void setCOLLECTOR1(Object cOLLECTOR1) {
        this.cOLLECTOR1 = cOLLECTOR1;
    }

    public List<Object> getEXHIBITIMAGES() {
        return eXHIBITIMAGES;
    }

    public void setEXHIBITIMAGES(List<Object> eXHIBITIMAGES) {
        this.eXHIBITIMAGES = eXHIBITIMAGES;
    }

    public Object getEXPERTISE1() {
        return eXPERTISE1;
    }

    public void setEXPERTISE1(Object eXPERTISE1) {
        this.eXPERTISE1 = eXPERTISE1;
    }

    public Object getFIELD1() {
        return fIELD1;
    }

    public void setFIELD1(Object fIELD1) {
        this.fIELD1 = fIELD1;
    }

    public Object getLOCATION() {
        return lOCATION;
    }

    public void setLOCATION(Object lOCATION) {
        this.lOCATION = lOCATION;
    }

    public Object getMAINTENANCE1() {
        return mAINTENANCE1;
    }

    public void setMAINTENANCE1(Object mAINTENANCE1) {
        this.mAINTENANCE1 = mAINTENANCE1;
    }

    public Object getMAKETECH1() {
        return mAKETECH1;
    }

    public void setMAKETECH1(Object mAKETECH1) {
        this.mAKETECH1 = mAKETECH1;
    }

    public Object getMATERIALTYPE1() {
        return mATERIALTYPE1;
    }

    public void setMATERIALTYPE1(Object mATERIALTYPE1) {
        this.mATERIALTYPE1 = mATERIALTYPE1;
    }

    public Object getOWNER1() {
        return oWNER1;
    }

    public void setOWNER1(Object oWNER1) {
        this.oWNER1 = oWNER1;
    }

    public Object getSECRETLEVEL1() {
        return sECRETLEVEL1;
    }

    public void setSECRETLEVEL1(Object sECRETLEVEL1) {
        this.sECRETLEVEL1 = sECRETLEVEL1;
    }

    public Object getSOURCE1() {
        return sOURCE1;
    }

    public void setSOURCE1(Object sOURCE1) {
        this.sOURCE1 = sOURCE1;
    }

    public Object getSTUFF1() {
        return sTUFF1;
    }

    public void setSTUFF1(Object sTUFF1) {
        this.sTUFF1 = sTUFF1;
    }

    public Object getTIME1() {
        return tIME1;
    }

    public void setTIME1(Object tIME1) {
        this.tIME1 = tIME1;
    }

}



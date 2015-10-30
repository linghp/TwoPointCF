package com.peoit.twopointcf.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/9/29.
 * description:
 */
public class ProjectBean implements Serializable{
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("projectName")
    @Expose
    public String projectName;
    @SerializedName("projectCity")
    @Expose
    public String projectCity;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("industryType")
    @Expose
    public String industryType;
    @SerializedName("projectIntro")
    @Expose
    public String projectIntro;
    @SerializedName("moneyUse")
    @Expose
    public String moneyUse;
    @SerializedName("totalStockMoney")
    @Expose
    public int totalStockMoney;
    @SerializedName("sellStockMoney")
    @Expose
    public Integer sellStockMoney;
    @SerializedName("perSellStockMoney")
    @Expose
    public Integer perSellStockMoney;
    @SerializedName("successCondition")
    @Expose
    public double successCondition;
    @SerializedName("lowestSellingPercentage")
    @Expose
    public double lowestSellingPercentage;
    @SerializedName("endDate")
    @Expose
    public String endDate;
    @SerializedName("dividendType")
    @Expose
    public String dividendType;
    @SerializedName("dividendPercent")
    @Expose
    public double dividendPercent;
    @SerializedName("stockholderPrivilege")
    @Expose
    public String stockholderPrivilege;
    @SerializedName("investorEarnestPercent")
    @Expose
    public double investorEarnestPercent;
    @SerializedName("stockType")
    @Expose
    public String stockType;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("projectPhotos")
    @Expose
    public List<String> projectPhotos = new ArrayList<String>();
    @SerializedName("businessLicenses")
    @Expose
    public List<String> businessLicenses = new ArrayList<String>();
    @SerializedName("personCredits")
    @Expose
    public List<String> personCredits = new ArrayList<String>();
    @SerializedName("industryLicense")
    @Expose
    public List<String> industryLicense = new ArrayList<String>();

    public int investedAmount;
    public int userInvestedAmount;
    public String userInvestedDate;
    public String marketAnalysis;
    public String sourceOfIncome;
    public String profitForecast;
    public String teamIntroducation;
    public int investUserAmount;
    public String publisherName;
    public String projectType;



    //以下为自己添加的字段
    public String mStockPercent;//占股比例
    public int mInvestMoney;//投资总额
    public int mInvestorEarnest;//保证金


    @Override
    public String toString() {
        return "ProjectBean{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectCity='" + projectCity + '\'' +
                ", address='" + address + '\'' +
                ", industryType='" + industryType + '\'' +
                ", projectIntro='" + projectIntro + '\'' +
                ", moneyUse='" + moneyUse + '\'' +
                ", totalStockMoney=" + totalStockMoney +
                ", sellStockMoney=" + sellStockMoney +
                ", perSellStockMoney=" + perSellStockMoney +
                ", successCondition=" + successCondition +
                ", lowestSellingPercentage=" + lowestSellingPercentage +
                ", endDate='" + endDate + '\'' +
                ", dividendType='" + dividendType + '\'' +
                ", dividendPercent=" + dividendPercent +
                ", stockholderPrivilege='" + stockholderPrivilege + '\'' +
                ", investorEarnestPercent=" + investorEarnestPercent +
                ", stockType=" + stockType +
                ", status='" + status + '\'' +
                ", projectPhotos=" + projectPhotos +
                ", businessLicenses=" + businessLicenses +
                ", personCredits=" + personCredits +
                ", industryLicense=" + industryLicense +
                '}';
    }


}

/*
 * finAPI RESTful Services
 * finAPI RESTful Services
 *
 * OpenAPI spec version: v1.64.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Balance data for a single day
 */
@ApiModel(description = "Balance data for a single day")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-02-05T12:19:21.458Z")
public class DailyBalance {
  @SerializedName("date")
  private String date = null;

  @SerializedName("balance")
  private BigDecimal balance = null;

  @SerializedName("income")
  private BigDecimal income = null;

  @SerializedName("spending")
  private BigDecimal spending = null;

  @SerializedName("transactions")
  private List<Long> transactions = new ArrayList<Long>();

  public DailyBalance date(String date) {
    this.date = date;
    return this;
  }

   /**
   * Date in the format &#39;YYYY-MM-DD HH:MM:SS.SSS&#39; (german time).
   * @return date
  **/
  @ApiModelProperty(example = "2018-01-01 00:00:00.000", required = true, value = "Date in the format 'YYYY-MM-DD HH:MM:SS.SSS' (german time).")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public DailyBalance balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Calculated account balance at the end of the given day.
   * @return balance
  **/
  @ApiModelProperty(example = "999.0", required = true, value = "Calculated account balance at the end of the given day.")
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public DailyBalance income(BigDecimal income) {
    this.income = income;
    return this;
  }

   /**
   * The sum of income of the given day.
   * @return income
  **/
  @ApiModelProperty(example = "99.99", required = true, value = "The sum of income of the given day.")
  public BigDecimal getIncome() {
    return income;
  }

  public void setIncome(BigDecimal income) {
    this.income = income;
  }

  public DailyBalance spending(BigDecimal spending) {
    this.spending = spending;
    return this;
  }

   /**
   * The sum of spending of the given day.
   * @return spending
  **/
  @ApiModelProperty(example = "-99.99", required = true, value = "The sum of spending of the given day.")
  public BigDecimal getSpending() {
    return spending;
  }

  public void setSpending(BigDecimal spending) {
    this.spending = spending;
  }

  public DailyBalance transactions(List<Long> transactions) {
    this.transactions = transactions;
    return this;
  }

  public DailyBalance addTransactionsItem(Long transactionsItem) {
    this.transactions.add(transactionsItem);
    return this;
  }

   /**
   * Identifiers of the transactions for the given day
   * @return transactions
  **/
  @ApiModelProperty(example = "[1,2,3]", required = true, value = "Identifiers of the transactions for the given day")
  public List<Long> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Long> transactions) {
    this.transactions = transactions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DailyBalance dailyBalance = (DailyBalance) o;
    return Objects.equals(this.date, dailyBalance.date) &&
        Objects.equals(this.balance, dailyBalance.balance) &&
        Objects.equals(this.income, dailyBalance.income) &&
        Objects.equals(this.spending, dailyBalance.spending) &&
        Objects.equals(this.transactions, dailyBalance.transactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, balance, income, spending, transactions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DailyBalance {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    income: ").append(toIndentedString(income)).append("\n");
    sb.append("    spending: ").append(toIndentedString(spending)).append("\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}


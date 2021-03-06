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
import io.swagger.client.model.Paging;
import io.swagger.client.model.UserInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Container for users information with paging info
 */
@ApiModel(description = "Container for users information with paging info")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-02-05T12:19:21.458Z")
public class PageableUserInfoList {
  @SerializedName("users")
  private List<UserInfo> users = new ArrayList<UserInfo>();

  @SerializedName("paging")
  private Paging paging = null;

  public PageableUserInfoList users(List<UserInfo> users) {
    this.users = users;
    return this;
  }

  public PageableUserInfoList addUsersItem(UserInfo usersItem) {
    this.users.add(usersItem);
    return this;
  }

   /**
   * List of users information
   * @return users
  **/
  @ApiModelProperty(required = true, value = "List of users information")
  public List<UserInfo> getUsers() {
    return users;
  }

  public void setUsers(List<UserInfo> users) {
    this.users = users;
  }

  public PageableUserInfoList paging(Paging paging) {
    this.paging = paging;
    return this;
  }

   /**
   * Information for pagination
   * @return paging
  **/
  @ApiModelProperty(required = true, value = "Information for pagination")
  public Paging getPaging() {
    return paging;
  }

  public void setPaging(Paging paging) {
    this.paging = paging;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageableUserInfoList pageableUserInfoList = (PageableUserInfoList) o;
    return Objects.equals(this.users, pageableUserInfoList.users) &&
        Objects.equals(this.paging, pageableUserInfoList.paging);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users, paging);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageableUserInfoList {\n");
    
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("    paging: ").append(toIndentedString(paging)).append("\n");
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


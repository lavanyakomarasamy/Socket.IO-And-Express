package com.avaya.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Agent {
	 @JsonProperty("id")
	  private String id = null;

	  @JsonProperty("isActivated")
	  private Boolean isActivated = null;

	  @JsonProperty("handle")
	  private String handle = null;

	  @JsonProperty("profileId")
	  private String profileId = null;

	  @JsonProperty("role")
	  private String role = null;

	  @JsonProperty("adapterId")
	  private String adapterId = null;

	  public Agent id(String id) {
	    this.id = id;
	    return this;
	  }

	  /**
	   * Get id
	   * @return id
	   **/
	  @JsonProperty("id")
	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }

	  public Agent isActivated(Boolean isActivated) {
	    this.isActivated = isActivated;
	    return this;
	  }

	  /**
	   * Get isActivated
	   * @return isActivated
	   **/
	  @JsonProperty("isActivated")
	  public Boolean getIsActivated() {
	    return isActivated;
	  }

	  public void setIsActivated(Boolean isActivated) {
	    this.isActivated = isActivated;
	  }

	  public Agent handle(String handle) {
	    this.handle = handle;
	    return this;
	  }

	  /**
	   * User handle
	   * @return handle
	   **/
	  @JsonProperty("handle")
	  public String getHandle() {
	    return handle;
	  }

	  public void setHandle(String handle) {
	    this.handle = handle;
	  }

	  public Agent profileId(String profileId) {
	    this.profileId = profileId;
	    return this;
	  }

	  /**
	   * Current profile Id
	   * @return profileId
	   **/
	  @JsonProperty("profileId")
	  public String getProfileId() {
	    return profileId;
	  }

	  public void setProfileId(String profileId) {
	    this.profileId = profileId;
	  }

	  public Agent role(String role) {
	    this.role = role;
	    return this;
	  }

	  /**
	   * Role
	   * @return role
	   **/
	  @JsonProperty("role")
	  public String getRole() {
	    return role;
	  }

	  public void setRole(String role) {
	    this.role = role;
	  }

	  public Agent adapterId(String adapterId) {
	    this.adapterId = adapterId;
	    return this;
	  }

	  /**
	   * Get adapterId
	   * @return adapterId
	   **/
	  @JsonProperty("adapterId")
	  public String getAdapterId() {
	    return adapterId;
	  }

	  public void setAdapterId(String adapterId) {
	    this.adapterId = adapterId;
	  }


	  @Override
	  public boolean equals(java.lang.Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    Agent agent = (Agent) o;
	    return Objects.equals(this.id, agent.id) &&
	        Objects.equals(this.isActivated, agent.isActivated) &&
	        Objects.equals(this.handle, agent.handle) &&
	        Objects.equals(this.profileId, agent.profileId) &&
	        Objects.equals(this.role, agent.role) &&
	        Objects.equals(this.adapterId, agent.adapterId);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(id, isActivated, handle, profileId, role, adapterId);
	  }


	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class Agent {\n");
	    
	    sb.append("    id: ").append(toIndentedString(id)).append("\n");
	    sb.append("    isActivated: ").append(toIndentedString(isActivated)).append("\n");
	    sb.append("    handle: ").append(toIndentedString(handle)).append("\n");
	    sb.append("    profileId: ").append(toIndentedString(profileId)).append("\n");
	    sb.append("    role: ").append(toIndentedString(role)).append("\n");
	    sb.append("    adapterId: ").append(toIndentedString(adapterId)).append("\n");
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

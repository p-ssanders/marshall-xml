package dev.samsanders.demo.xml;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleThing {

  private String name;
  private Date time;

  public SimpleThing() {}

  public SimpleThing(String name, Date time) {
    this.name = name;
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}

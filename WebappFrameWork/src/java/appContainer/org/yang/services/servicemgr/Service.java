/* Generated by Together */

package org.yang.services.servicemgr;
import java.util.Properties;
import org.yang.web.applicationContainer.Passport;

public interface Service
{
    String getName();

    void initialize(Properties prop, Passport passport);

    void destroy();

    Area[] allAreas();

    boolean containArea(String areaname);
}

package domainapp.dom.simple.financials;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.ObjectContracts;

import javax.jdo.JDOHelper;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

/**
 * Created by Administrator on 9/30/2015.
 */

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple",
        table = "PaymentMediaType"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column="id")
@javax.jdo.annotations.Version(
        strategy= VersionStrategy.VERSION_NUMBER,
        column="version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findAllPaymentMediaTypes", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.simple.PaymentMediaType "),
        @javax.jdo.annotations.Query(
                name = "findPaymentMediaTypesByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.simple.PaymentMediaType "
                        + "WHERE name.indexOf(:name) >= 0 "),
        @javax.jdo.annotations.Query(
                name = "findPaymentMediaTypeById", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.simple.PaymentMediaType "
                        + "WHERE paymentMediaTypeId.indexOf(:paymentMediaTypeId) >= 0 ")
})


@javax.jdo.annotations.Unique(name="PaymentMediaType_Id_UNQ", members = {"paymentMediaTypeId"})
@DomainObject(autoCompleteAction = "findPaymentMediaTypesByName", autoCompleteRepository = PaymentMediaTypes.class, auditing= Auditing.ENABLED)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT,
        cssClassFa = "fa-credit-card"

)
public class PaymentMediaType implements Comparable<PaymentMediaType> {

    //region > ID (property)

    private String paymentMediaTypeId;

    @javax.jdo.annotations.Column(allowsNull="false", length = 10)

    public String getPaymentMediaTypeId() {
        return paymentMediaTypeId;
    }

    public void setPaymentMediaTypeId(final String paymentMediaTypeId) {
        this.paymentMediaTypeId = paymentMediaTypeId;
    }

    // endregion

    //region > name (property)

    private String name;

    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    @Title(sequence="1")

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    // endregion

    //region > version (derived property)
    public Long getVersionSequence() {
        return (Long) JDOHelper.getVersion(this);
    }
    //endregion

    //region > compareTo

    @Override
    public int compareTo(final PaymentMediaType other) {
        return ObjectContracts.compare(this, other, "paymentMediaTypeId");
    }

    //endregion
}

package domainapp.dom.simple.businesses;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

import java.util.List;

/**
 * Created by Administrator on 10/1/2015.
 */
@DomainService(
        repositoryFor = BusinessSubscription.class)
@DomainServiceLayout(named = "Businesses",menuOrder = "90")

public class BusinessSubscriptions {

    //region > listAllBusinessSubscriptions (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(name="businessSubscriptions",sequence = "1")
    public List<BusinessSubscription> listAllBusinessSubscriptions() {
        return container.allInstances(BusinessSubscription.class);
    }
    //endregion



    //region > addSubscription (action)
    public static class CreateDomainEvent extends ActionDomainEvent<BusinessSubscriptions> {
        public CreateDomainEvent(final BusinessSubscriptions source, final Identifier identifier, final Object... arguments) {
            super(source, identifier, arguments);
        }
    }

    @Action(
            domainEvent = CreateDomainEvent.class
    )
    @MemberOrder(name="businessSubscriptions",sequence = "2")
    public BusinessSubscription addSubscription(
            final @ParameterLayout(named="Business Subscription Id") Long businessSubscriptionId,
            final @ParameterLayout(named="Statement Day of Month") Integer statementDom

    )
    {
        final BusinessSubscription obj = container.newTransientInstance(BusinessSubscription.class);
        obj.setBusinessSubscriptionId(businessSubscriptionId);
        obj.setStatementDom(statementDom);
/*
        obj.setPmFinancialInst(pmFinancialInst);

        obj.setPaymentMedia(paymentMedia);
        obj.setCmIsAccount(cmIsAccount);
        obj.setCmFinancialInst(cmFinancialInst);
        obj.setCollectionMedia(collectionMedia);
        obj.setCmIsCreditCard(cmIsCreditCard);
        obj.setCmCcPaymentMediaType(cmCcPaymentMediaType);
        obj.setCmCreditCard(cmCreditCard);
        obj.setCmName(cmName);
        obj.setCmExpirationMonth(cmExpirationMonth);
        obj.setCmExpirationYear(cmExpirationYear);
        obj.setCmZip(cmZip);
        obj.setSubscriptionTime(clockService.nowAsDateTime());
*/
        obj.setStatus(BusinessStatus.REGISTRATION);
        container.persistIfNotAlready(obj);
        return obj;
    }

/*
    //Default for parameter 5 cmIsAccount above
    public Boolean default5addSubscription() {
        return Boolean.TRUE;
    }

    //Default for parameter 6 cmIsCreditCard above
    public Boolean default6addSubscription() {
        return Boolean.FALSE;
    }
*/

    //endregion

    //region > injected services

    @javax.inject.Inject
    DomainObjectContainer container;

    //endregion
}

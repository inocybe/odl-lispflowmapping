/*
 * Copyright (c) 2015 Cisco Systems, Inc.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.lispflowmapping.implementation.util;

import java.util.Arrays;
import java.util.List;

import org.opendaylight.lispflowmapping.lisp.util.LispAddressStringifier;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.lisp.proto.rev151105.eid.container.Eid;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.lisp.proto.rev151105.mapping.record.container.MappingRecord;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.EidUri;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.MappingOrigin;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.SiteId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.db.instance.AuthenticationKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.db.instance.AuthenticationKeyBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.db.instance.Mapping;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.db.instance.MappingBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.lfm.mappingservice.rev150906.mapping.authkey.container.MappingAuthkey;

/**
 * @author Florin Coras
 *
 */
public final class DSBEInputUtil {
    // Utility class, should not be instantiated
    private DSBEInputUtil() {
    }

    public static Mapping toMapping(MappingOrigin origin, Eid key, SiteId siteId, MappingRecord record) {
        List<SiteId> siteIds = (siteId != null) ? Arrays.asList(siteId) : null;
        return new MappingBuilder().setEidUri(new EidUri(LispAddressStringifier.getURIString(key)))
                .setOrigin(origin).setSiteId(siteIds).setMappingRecord(record).build();
    }

    public static Mapping toMapping(MappingOrigin origin, Eid key) {
        MappingBuilder mb = new MappingBuilder();
        mb.setEidUri(new EidUri(LispAddressStringifier.getURIString(key)));
        mb.setOrigin(origin);
        return mb.build();
    }

    public static AuthenticationKey toAuthenticationKey(Eid key, MappingAuthkey authKey) {
        AuthenticationKeyBuilder akb = new AuthenticationKeyBuilder();
        akb.setEidUri(new EidUri(LispAddressStringifier.getURIString(key)));
        akb.setEid(key);
        akb.setMappingAuthkey(authKey);
        return akb.build();
    }
}

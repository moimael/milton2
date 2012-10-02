/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.milton.http;

import io.milton.resource.AccessControlledResource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author brad
 */
public class AclUtils {
    
    /**
     * Recurisve function which checks the given collection of priviledges, 
     * and checks inside the contains property of those priviledges
     * 
     * Returns true if the required priviledge is directly present in the collection
     * or is implied
     * 
     * @param required
     * @param privs
     * @return 
     */
    public static boolean containsPriviledge(AccessControlledResource.Priviledge required, Iterable<AccessControlledResource.Priviledge> privs) {
        for (AccessControlledResource.Priviledge p : privs) {
            if (p.equals(required)) {
                return true;
            }
            if( containsPriviledge(required, p.contains)) {
                return true;
            }
        }
        return false;
    }      
    
    public static Set<AccessControlledResource.Priviledge> asSet(AccessControlledResource.Priviledge ... privs) {
        Set<AccessControlledResource.Priviledge> set = new HashSet<AccessControlledResource.Priviledge>(privs.length);
        set.addAll(Arrays.asList(privs));
        return set;
    }
}
/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package com.muzima.api.model.resolver;

import java.io.IOException;

public class SearchFormResolver extends BaseOpenmrsResolver {

    private static final String REPRESENTATION = "?v=custom:(uuid,name,tags,description)";

    /**
     * Return the full REST resource based on the search string passed to the method.
     *
     * @param searchString the search string
     * @return full URI to the REST resource
     */
    @Override
    public String resolve(final String searchString) throws IOException {
        //This resolver currently ignore search string and fetch all forms
        return getConfiguration().getServer() + "/ws/rest/v1/muzimaforms/form" + REPRESENTATION;

    }
}

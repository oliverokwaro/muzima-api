/*
 * Copyright (c) 2014. The Trustees of Indiana University.
 *
 * This version of the code is licensed under the MPL 2.0 Open Source license with additional
 * healthcare disclaimer. If the user is an entity intending to commercialize any application
 * that uses this code in a for-profit venture, please contact the copyright holder.
 */

package com.muzima.api.model.resolver;

import com.muzima.api.model.algorithm.ConceptAlgorithm;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * TODO: Write brief description about the class here.
 */
public class SearchConceptNumericResolver extends BaseOpenmrsResolver {

    private static final String REPRESENTATION =
            "?v=custom:" + ConceptAlgorithm.CONCEPT_NUMERIC_STANDARD_REPRESENTATION;

    /**
     * Return the full REST resource based on the parameters passed to the method.
     *
     * @param resourceParams the parameters of the resource to resolved.
     * @return full uri to the REST resource.
     */
    @Override
    public String resolve(final Map<String, String> resourceParams) throws IOException {
        StringBuilder paramBuilder = new StringBuilder();
        for (String key : resourceParams.keySet()) {
            paramBuilder.append("&").append(key).append("=").append(URLEncoder.encode(resourceParams.get(key), "UTF-8"));
        }
        return getConfiguration().getServer() + "/ws/rest/v1/concept" + REPRESENTATION + paramBuilder.toString();
    }
}

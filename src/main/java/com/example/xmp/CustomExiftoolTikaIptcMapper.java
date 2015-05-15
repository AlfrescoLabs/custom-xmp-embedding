/*
 * Copyright (C) 2005-2015 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.xmp;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.tika.metadata.Property;
import org.apache.tika.parser.exiftool.ExiftoolTikaIptcMapper;

/**
 * Customer mapper which adds a single <code>sampleSchema:Text</code> mapping
 * to the default returned by {@link ExiftoolTikaIptcMapper}.
 */
public class CustomExiftoolTikaIptcMapper extends ExiftoolTikaIptcMapper
{
    Property CUSTOM_XMP_TEXT = Property.internalText("XMP-sampleSchema:Text");
    
    @Override
    public Map<Property, List<Property>> getTikaToExiftoolMetadataMap() {
        Map<Property, List<Property>> map = super.getTikaToExiftoolMetadataMap();
        map.put(CUSTOM_XMP_TEXT, Collections.singletonList(CUSTOM_XMP_TEXT));
        return map;
    }
}

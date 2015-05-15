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

import org.alfresco.repo.content.metadata.TikaImageMetadataEmbedderOnly;
import org.apache.tika.embedder.Embedder;
import org.apache.tika.embedder.exiftool.ExiftoolExternalEmbedder;

/**
 * Overrides {@link TikaImageMetadataEmbedderOnly} to be able to set a different
 * mapper in the embedder.
 */
public class CustomTikaImageMetadataEmbedderOnly extends TikaImageMetadataEmbedderOnly
{
    private String runtimeExiftoolExecutable;
    
    @Override
    public void setRuntimeExiftoolExecutable(String runtimeExiftoolExecutable)
    {
        // Check for unresolved Spring placeholder property
        if (!(new String("${" + PROPERTY_EXIFTOOL_EXECUTABLE + "}").equals(runtimeExiftoolExecutable)))
        {
            this.runtimeExiftoolExecutable = runtimeExiftoolExecutable;
        }
        super.setRuntimeExiftoolExecutable(runtimeExiftoolExecutable);
    }

    @Override
    protected Embedder getEmbedder()
    {
        return new ExiftoolExternalEmbedder(new CustomExiftoolTikaIptcMapper(), runtimeExiftoolExecutable);
    }

}

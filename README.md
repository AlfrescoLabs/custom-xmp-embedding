This is an example of a customization to enable embedding of custom XMP metadata in conjuction with
Alfresco Media Management.

Note that at this time the source and individual artifacts of the Media Management are not publicly
available so this project won't compile unless you have access to the MM source locally
or via private Maven repo.

The ability to map to a single `sampleSchema:Text` field 
(included in Adobe's example metadata panels) is added via the `CustomExiftoolTikaIptcMapper` class.

Entries can then be added to global properties to define the data model property to
XMP field mapping.  For example, to embed `cm:title` into the `sampleSchema:Text` field:

    metadata.embedder.TikaExifTool.embed.namespace.prefix.cm=http://www.alfresco.org/model/content/1.0
    metadata.embedder.TikaExifTool.embed.cm\:title=XMP-sampleSchema\:Text

[ExifTool must also be configured](http://www.sno.phy.queensu.ca/~phil/exiftool/faq.html#Q11)
to support this custom namespace.  In the example above:

    ...
        'Image::ExifTool::XMP::Main' => {
            # namespace definition for examples 8 to 11
            xxx => { # <-- must be the same as the NAMESPACE prefix
                SubDirectory => {
                    TagTable => 'Image::ExifTool::UserDefined::xxx',
                    # (see the definition of this table below)
                },
            },
            # add more user-defined XMP namespaces here...
            sampleSchema => { # <-- must be the same as the NAMESPACE prefix
                SubDirectory => {
                    TagTable => 'Image::ExifTool::UserDefined::sampleSchema',
                    # (see the definition of this table below)
                },
            },
        },
    ...
    %Image::ExifTool::UserDefined::sampleSchema = (
        GROUPS => { 0 => 'XMP', 1 => 'XMP-sampleSchema', 2 => 'Image' },
        NAMESPACE => { 'sampleSchema' => 'http://my.sampleSchema.namespace/' },
        WRITABLE => 'string',
        # Example  XMP-sampleSchema:Text
        Text => { Writable => 'string' }
    );

You can then trigger embedding through various means like a rule which runs the
'Embed properties as metadata in file' action when content is updated in a space.

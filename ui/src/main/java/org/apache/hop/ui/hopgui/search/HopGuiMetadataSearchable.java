package org.apache.hop.ui.hopgui.search;

import org.apache.hop.core.search.ISearchable;
import org.apache.hop.core.search.ISearchableCallback;
import org.apache.hop.metadata.api.IHopMetadata;
import org.apache.hop.metadata.api.IHopMetadataProvider;
import org.apache.hop.metadata.api.IHopMetadataSerializer;
import org.apache.hop.ui.core.metastore.MetadataManager;
import org.apache.hop.ui.hopgui.HopGui;

public class HopGuiMetadataSearchable implements ISearchable<IHopMetadata> {

  private IHopMetadataProvider metadataProvider;
  private IHopMetadataSerializer<IHopMetadata> serializer;
  private IHopMetadata searchableObject;
  private Class<IHopMetadata> managedClass;

  public HopGuiMetadataSearchable( IHopMetadataProvider metadataProvider, IHopMetadataSerializer<IHopMetadata> serializer, IHopMetadata searchableObject, Class<IHopMetadata> managedClass ) {
    this.metadataProvider = metadataProvider;
    this.serializer = serializer;
    this.searchableObject = searchableObject;
    this.managedClass = managedClass;
  }

  @Override public String getLocation() {
    return metadataProvider.getDescription();
  }

  @Override public String getName() {
    return searchableObject.getName();
  }

  @Override public String getType() {
    return serializer.getDescription();
  }

  @Override public String getFilename() {
    return null;
  }

  @Override public ISearchableCallback getSearchCallback() {
    return ( searchable, searchResult ) -> {
      // Open the metadata object...
      //
      new MetadataManager( HopGui.getInstance().getVariables(), HopGui.getInstance().getMetadataProvider(), searchable.getClass() )
        .editMetadata(searchable.getName());
    };
  }

  /**
   * Gets searchableObject
   *
   * @return value of searchableObject
   */
  @Override public IHopMetadata getSearchableObject() {
    return searchableObject;
  }

  /**
   * @param searchableObject The searchableObject to set
   */
  public void setSearchableObject( IHopMetadata searchableObject ) {
    this.searchableObject = searchableObject;
  }

  /**
   * Gets managedClass
   *
   * @return value of managedClass
   */
  public Class<IHopMetadata> getManagedClass() {
    return managedClass;
  }

  /**
   * @param managedClass The managedClass to set
   */
  public void setManagedClass( Class<IHopMetadata> managedClass ) {
    this.managedClass = managedClass;
  }
}

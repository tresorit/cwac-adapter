/***
  Copyright (c) 2008-2009 CommonsWare, LLC
  Portions (c) 2009 Google, Inc.
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/    

package com.commonsware.cwac.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

/**
 * Adapter that delegates to a wrapped LisAdapter, much as
 * CursorWrapper delegates to a wrapped Cursor.
 */
public class ExpandableAdapterWrapper extends BaseExpandableListAdapter {
  private BaseExpandableListAdapter wrapped=null;

  /**
    * Constructor wrapping a supplied ListAdapter
    */
  public ExpandableAdapterWrapper(BaseExpandableListAdapter wrapped) {
    super();
    
    this.wrapped=wrapped;
    
    wrapped.registerDataSetObserver(new DataSetObserver() {
      public void onChanged() {
        notifyDataSetChanged();
      }
      
      public void onInvalidated() {
        notifyDataSetInvalidated();
      }
    });
  }

  @Override
  public boolean areAllItemsEnabled() {
    return(wrapped.areAllItemsEnabled());
  }

  /**
    * Returns the ListAdapter that is wrapped by the endless
    * logic.
    */
  protected BaseExpandableListAdapter getWrappedAdapter() {
    return(wrapped);
  }

@Override
public Object getChild(int groupPosition, int childPosition) {
	return wrapped.getChild(groupPosition, childPosition);
}

@Override
public long getChildId(int groupPosition, int childPosition) {
	return wrapped.getChildId(groupPosition, childPosition);
}

@Override
public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
	return wrapped.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
}

@Override
public int getChildrenCount(int groupPosition) {
	return wrapped.getChildrenCount(groupPosition);
}

@Override
public Object getGroup(int groupPosition) {
	return wrapped.getGroup(groupPosition);
}

@Override
public int getGroupCount() {
	return wrapped.getGroupCount();
}

@Override
public long getGroupId(int groupPosition) {
	return wrapped.getGroupId(groupPosition);
}

@Override
public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	return wrapped.getGroupView(groupPosition, isExpanded, convertView, parent);
}

@Override
public boolean hasStableIds() {
	return wrapped.hasStableIds();
}

@Override
public boolean isChildSelectable(int groupPosition, int childPosition) {
	return wrapped.isChildSelectable(groupPosition, childPosition);
}
}
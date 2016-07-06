package berlin.htw.schneider.viktor.sharknet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharksystem.sharknet.api.Interest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by viktorowich on 20/06/16.
 */
public class InterestsListAdapter extends BaseExpandableListAdapter
{
    private Activity context;
    private List<TXSemanticTag> txSemanticTags;
    private HashMap<TXSemanticTag,List<SemanticTag>> semanticTagListHashMap;

    public InterestsListAdapter (Activity context,
                                 List<TXSemanticTag> txSemanticTags)
    {
        this.context = context;
        this.txSemanticTags = txSemanticTags;
        this.semanticTagListHashMap = null;

        for(TXSemanticTag sTag : this.txSemanticTags)
        {
            List <SemanticTag> sema =null;
            // TODO: wirft hier einen fehler
            while (sTag.subTags().hasMoreElements())
            {
                sema.add(sTag.subTags().nextElement());
            }
            this.semanticTagListHashMap.put(sTag,sema);
        }
    }


    @Override
    public int getGroupCount()
    {
        return this.txSemanticTags.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return semanticTagListHashMap.get(this.txSemanticTags.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this.txSemanticTags.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return semanticTagListHashMap.get(this.txSemanticTags.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition,
                             boolean isExpanded,
                             View convertView,
                             ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.line_item_interest,null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.interest_name);
        name.setText(this.txSemanticTags.get(groupPosition).getName());


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition,
                             int childPosition,
                             boolean isLastChild,
                             View convertView,
                             ViewGroup parent)
    {
        //this.interest = this.interests.get(groupPosition).getChilds().get(childPosition);

        LayoutInflater inflater = this.context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.line_sub_item_interest, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.sub_interest);
        name.setText(semanticTagListHashMap.get(this.txSemanticTags.get(groupPosition)).get(childPosition).getName());

        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

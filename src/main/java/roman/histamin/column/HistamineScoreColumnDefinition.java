package roman.histamin.column;

import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.ui.contentapp.configuration.column.ConfiguredColumnDefinition;

import java.util.Optional;

import javax.jcr.Node;
import javax.jcr.Property;

import com.machinezoo.noexception.Exceptions;

public class HistamineScoreColumnDefinition extends ConfiguredColumnDefinition<Node> {

    public HistamineScoreColumnDefinition() {
        setValueProvider(ValueProvider.class);
        this.setWidth(75);
    }

    public static class ValueProvider implements com.vaadin.data.ValueProvider<Node, Integer> {

        @Override
        public Integer apply(Node node) {
            return Exceptions.wrap().get(() -> NodeUtil.isNodeType(node, NodeTypes.Folder.NAME)) ? null :
                    getScore(node, "positiveSources") - getScore(node, "negativeSources");
        }

        public Integer getScore(Node node, String relativePath) {
            return Optional.ofNullable(PropertyUtil.getPropertyOrNull(node, relativePath))
                    .map(Exceptions.wrap().function(Property::getValues))
                    .map(values -> values.length)
                    .orElse(0);
        }
    }

}

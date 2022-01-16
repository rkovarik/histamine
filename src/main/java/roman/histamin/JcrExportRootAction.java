package roman.histamin;

import info.magnolia.commands.CommandsManager;
import info.magnolia.context.Context;
import info.magnolia.ui.ValueContext;
import info.magnolia.ui.contentapp.action.JcrExportAction;
import info.magnolia.ui.contentapp.action.JcrExportActionDefinition;
import info.magnolia.ui.contentapp.async.AsyncActionExecutor;
import info.magnolia.ui.datasource.jcr.JcrDatasource;

import java.util.Collections;
import java.util.Set;
import java.util.function.UnaryOperator;

import javax.inject.Inject;
import javax.jcr.Node;

public class JcrExportRootAction extends JcrExportAction {

    private final JcrDatasource datasource;

    @Inject
    public JcrExportRootAction(JcrExportActionDefinition definition, CommandsManager commandsManager, ValueContext<Node> valueContext, Context context, AsyncActionExecutor asyncActionExecutor, JcrDatasource datasource) {
        super(definition, commandsManager, valueContext, context, asyncActionExecutor, datasource);
        this.datasource = datasource;
    }

    @Override
    public void execute() {
        getValueContext().set(getValueContext().getSingle().orElseGet(datasource::getRoot));
        super.execute();
    }
}

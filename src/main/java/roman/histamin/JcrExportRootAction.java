package roman.histamin;

import info.magnolia.commands.CommandsManager;
import info.magnolia.context.Context;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.module.InstallContext;
import info.magnolia.module.InstallContextImpl;
import info.magnolia.module.delta.QueryTask;
import info.magnolia.ui.ValueContext;
import info.magnolia.ui.contentapp.action.JcrExportAction;
import info.magnolia.ui.contentapp.action.JcrExportActionDefinition;
import info.magnolia.ui.contentapp.async.AsyncActionExecutor;
import info.magnolia.ui.datasource.jcr.JcrDatasource;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Property;

import com.machinezoo.noexception.Exceptions;

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
//        try {
//            InstallContextImpl installContext = new InstallContextImpl(null);
//            new QueryTask("", "", "food", "select * from [nt:base]") {
//                @Override
//                protected void operateOnNode(InstallContext installContext, Node node) {
//                    Stream.of("positiveSources", "negativeSources")
//                            .map(s -> PropertyUtil.getPropertyOrNull(node, s))
//                            .filter(Objects::nonNull)
//                            .filter(Exceptions.wrap().predicate(property -> property.getValues().length > 0))
//                            .peek(Exceptions.wrap().consumer(property -> property.setValue(new String[]{"af6641d8-cf5e-4954-a037-21112c51a451"})))
//                            .forEach(Exceptions.wrap().consumer(r -> r.getSession().save()));
//                }
//            }.execute(installContext);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

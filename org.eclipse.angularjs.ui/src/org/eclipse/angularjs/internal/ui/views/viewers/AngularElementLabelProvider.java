package org.eclipse.angularjs.internal.ui.views.viewers;

import org.eclipse.angularjs.internal.ui.ImageResource;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import tern.ITernProject;
import tern.angular.AngularType;
import tern.server.protocol.outline.IJSNode;

public class AngularElementLabelProvider extends LabelProvider implements ILabelDecorator {

	public static final AngularElementLabelProvider INSTANCE = new AngularElementLabelProvider();

	@Override
	public Image getImage(Object element) {
		if (element instanceof IJSNode) {
			String kind = ((IJSNode) element).getKind();
			AngularType type = AngularType.get(kind);
			if (type == null) {
				return null;
			}
			switch (type) {
			case module:
				return ImageResource.getImage(ImageResource.IMG_ANGULARJS);
			case controller:
				return ImageResource.getImage(ImageResource.IMG_CONTROLLER);
			case directive:
				return ImageResource.getImage(ImageResource.IMG_DIRECTIVE);
			case filter:
				return ImageResource.getImage(ImageResource.IMG_FILTER);
			case factory:
				return ImageResource.getImage(ImageResource.IMG_FACTORY);
			case provider:
				return ImageResource.getImage(ImageResource.IMG_PROVIDER);
			case service:
				return ImageResource.getImage(ImageResource.IMG_SERVICE);
			default:
				return null;
			}
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IJSNode) {
			return ((IJSNode) element).getName();
		}
		return super.getText(element);
	}

	@Override
	public Image decorateImage(Image image, Object element) {
		return null;
	}

	@Override
	public String decorateText(String text, Object element) {
		if (element instanceof IJSNode) {
			IJSNode node = ((IJSNode) element);
			ITernProject project = node.getTernProject();
			return node.getName() + " - " + project.getName() + "/" + node.getFile();
		}
		return null;
	}

}
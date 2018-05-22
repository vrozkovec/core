package com.googlecode.wicket.jquery.ui.samples.jqueryui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.samples.component.UploadDialog;
import com.googlecode.wicket.jquery.ui.widget.dialog.DialogButton;

public class UploadDialogPage extends AbstractDialogPage // NOSONAR
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UploadDialogPage.class);

	public UploadDialogPage()
	{
		final Form<Void> form = new Form<Void>("form");
		this.add(form);

		// FeedbackPanel //
		final FeedbackPanel feedback = new JQueryFeedbackPanel("feedback");
		form.add(feedback);

		// Dialog //
		final UploadDialog dialog = new UploadDialog("dialog", "Upload file") { // NOSONAR

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, DialogButton button)
			{
				super.onSubmit(target, button);

				try
				{
					// simulates uploading...
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug(e.getMessage(), e);
					}
				}

				FileUpload fu = this.getModelObject();

				if (fu != null)
				{
					this.info(String.format("Uploaded file: '%s'", fu.getClientFileName()));
				}
			}

			@Override
			public void onClose(IPartialPageRequestHandler handler, DialogButton button)
			{
				super.onClose(handler, button);

				handler.add(feedback);
			}
		};

		this.add(dialog); // the dialog is not within the form

		// Buttons //
		form.add(new AjaxButton("open") { // NOSONAR

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target)
			{
				dialog.open(target);
			}
		});
	}
}

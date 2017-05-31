import java.util.ListIterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Button;

public class UI_remove extends Shell {
	protected static Table table;
	protected static boolean isWorking = false;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static UI_remove shell;
	
	public static void main() {
		isWorking = true;
		try {
			Display display = Display.getDefault();
			shell = new UI_remove(display);
			shell.open();
			
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		isWorking = false;
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public UI_remove(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setBounds(417, 10, 70, 20);
		lblName.setText("Name:");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(418, 140, 224, 21);
		
		Button btnMale = new Button(composite, SWT.RADIO);
		btnMale.setSelection(true);
		btnMale.setBounds(12, 0, 57, 20);
		btnMale.setText("Male");
		
		Button btnFemale = new Button(composite, SWT.RADIO);
		btnFemale.setBounds(75, 0, 72, 20);
		btnFemale.setText("Female");
		
		Button btnDoubt = new Button(composite, SWT.RADIO);
		btnDoubt.setBounds(153, 0, 66, 20);
		btnDoubt.setText("Doubt");
		
		Text text = new Text(this, SWT.BORDER);
		text.setBounds(417, 36, 207, 26);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBounds(417, 88, 225, 20);
		
		Button btnTrue = new Button(composite_1, SWT.RADIO);
		btnTrue.setSelection(true);
		btnTrue.setBounds(10, 0, 57, 20);
		btnTrue.setText("True");
		
		Label text_age = new Label(this, SWT.NONE);
		text_age.setBounds(468, 166, 70, 20);
		text_age.setText("0");
		
		Button btnFalse = new Button(composite_1, SWT.RADIO);
		btnFalse.setBounds(73, 0, 57, 20);
		btnFalse.setText("False");
		Scale scale = new Scale(this, SWT.NONE);
		scale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_age.setText(Integer.toString(scale.getSelection()));
			}
		});
		scale.setMaximum(75);
		scale.setBounds(422, 192, 220, 48);
		
		Button btnRemove = new Button(this, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sex;
				if (btnMale.getSelection()) {
					sex = new String("male");
				} else if (btnFemale.getSelection()) {
					sex = new String("female");
				} else sex = new String ("doubt");
				
				Baby baby = new Baby(text.getText(), btnTrue.getSelection() == true, sex, scale.getSelection());
				ListIterator<Baby> it = UI_main.myList.listIterator();
                boolean checkCritearia = false;
                while (it.hasNext()) {
                    if (baby.compareTo(it.next()) == 0) {
                        it.remove();
                        checkCritearia = true;
                        UI_main.refresh();
                        break;
                    }
                }
				if (!checkCritearia) {
					UI_warning.setWarning("Element not found");
					UI_warning.main();
				}
			}
		});
		btnRemove.setBounds(521, 246, 121, 26);
		btnRemove.setText("Remove selected");
		
		Button btnRemoveAll = new Button(this, SWT.NONE);
		btnRemoveAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				UI_main.myList.clear();
				UI_main.refresh();
			}
		});
		btnRemoveAll.setBounds(281, 39, 128, 23);
		btnRemoveAll.setText("Remove all");
		
		Button btnRemoveLast = new Button(this, SWT.CHECK);
		btnRemoveLast.setBounds(281, 88, 111, 20);
		btnRemoveLast.setText("remove last");
		
		Button btnRemoveFirst = new Button(this, SWT.CHECK);
		btnRemoveFirst.setBounds(281, 114, 111, 20);
		btnRemoveFirst.setText("remove first");
		
		Button btnRemoveLower = new Button(this, SWT.CHECK);
		btnRemoveLower.setBounds(281, 140, 111, 20);
		btnRemoveLower.setText("remove lower");
		
		Button btnRemoveGreater = new Button(this, SWT.CHECK);
		btnRemoveGreater.setBounds(281, 166, 126, 20);
		btnRemoveGreater.setText("remove greater");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(281, 10, 70, 20);
		lblNewLabel.setText("Type:");
		
		Label lblFree = new Label(this, SWT.NONE);
		lblFree.setBounds(417, 62, 70, 20);
		lblFree.setText("Free:");
		
		Label lblSex = new Label(this, SWT.NONE);
		lblSex.setBounds(417, 114, 70, 20);
		lblSex.setText("Sex");
		
		Label lblAge = new Label(this, SWT.NONE);
		lblAge.setBounds(417, 166, 33, 20);
		lblAge.setText("Age:");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setBounds(448, 285, 90, 30);
		btnCancel.setText("Cancel");
		
		Button btnAccept = new Button(this, SWT.NONE);
		btnAccept.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRemoveFirst.getSelection() && !UI_main.myList.isEmpty()) {
					UI_main.myList.removeFirst();
				}
				if (btnRemoveLast.getSelection() && !UI_main.myList.isEmpty()) {
					UI_main.myList.removeLast();
				}
				String sex;
				if (btnMale.getSelection()) {
					sex = new String("male");
				} else if (btnFemale.getSelection()) {
					sex = new String("female");
				} else sex = new String ("doubt");
				Baby baby = new Baby(text.getText(), btnTrue.getSelection() == true, sex, scale.getSelection());
				if (btnRemoveLower.getSelection() && !UI_main.myList.isEmpty()) {
					ListIterator<Baby> it = UI_main.myList.listIterator();
	                while (it.hasNext()) {
	                    if (baby.compareTo(it.next()) > 0) {
	                        it.remove();
	                    }
	                }
				}
				if (btnRemoveGreater.getSelection() && !UI_main.myList.isEmpty()) {
					ListIterator<Baby> it = UI_main.myList.listIterator();
	                while (it.hasNext()) {
	                    if (baby.compareTo(it.next()) < 0) {
	                        it.remove();
	                    }
	                }
				}
				table.removeAll();
    			for (int i = 0; i < UI_main.myList.size(); i++) {
    				TableItem item = new TableItem(table, SWT.NONE);
    				item.setText(0, UI_main.myList.get(i).getName());
    				item.setText(1, UI_main.myList.get(i).getFree());
    				item.setText(2, UI_main.myList.get(i).getSex());
    				item.setText(3, UI_main.myList.get(i).getAge());
    			}
    			UI_main.refresh();
			}
		});
		btnAccept.setText("Accept");
		btnAccept.setBounds(552, 285, 90, 30);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText(table.getSelection()[0].getText(0));
				if (table.getSelection()[0].getText(1).equals("true")) {
					btnFalse.setSelection(false);
					btnTrue.setSelection(true);
				} else {
					btnTrue.setSelection(false);
					btnFalse.setSelection(true);
				}
				String curSex = table.getSelection()[0].getText(2);
				if (curSex.equals("male")) {
					btnMale.setSelection(true);
					btnFemale.setSelection(false);
					btnDoubt.setSelection(false);
				} else if (curSex.equals("female")) {
					btnMale.setSelection(false);
					btnFemale.setSelection(true);
					btnDoubt.setSelection(false);
				} else {
					btnMale.setSelection(false);
					btnFemale.setSelection(false);
					btnDoubt.setSelection(true);
				}
				scale.setSelection(Integer.parseInt(table.getSelection()[0].getText(3)));
				text_age.setText(Integer.toString(scale.getSelection()));
				//text.setText();
				//text_age.setText(Integer.toString(table.getSelectionIndex()));
			}
		});
		table.setBounds(10, 12, 251, 303);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		data.widthHint = 300;
		table.setLayoutData(data);
		
		String headers[] = { "Name", "Free", "Sex", "Age" };
		for (int i = 0; i < headers.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(headers[i]);
			table.getColumn(i).pack();
		}
		for (int i = 0; i < UI_main.myList.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, UI_main.myList.get(i).getName());
			item.setText(1, UI_main.myList.get(i).getFree());
			item.setText(2, UI_main.myList.get(i).getSex());
			item.setText(3, UI_main.myList.get(i).getAge());
		}
		for (int i = 0; i < headers.length; i++) {
			table.getColumn(i).pack();
		}
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Remove");
		setSize(685, 372);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

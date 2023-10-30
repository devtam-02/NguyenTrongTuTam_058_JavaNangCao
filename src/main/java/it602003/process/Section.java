package it602003.process;

import java.util.ArrayList;

import it602003.objects.SectionObject;

public interface Section {
	public ArrayList<SectionObject> getSectionObjects(SectionObject similar, byte total);
	public boolean addSection(SectionObject item);
}

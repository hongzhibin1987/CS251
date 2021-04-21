interface Holder
{
	public void add(Object obj); //Put obj in the Holder
	public void remove(Object obj) throws Exception; //Remove obj from the Holder or throw an exception if there is no such obj in the Holder.
	public boolean contains(Object obj); //Does Holder already hold obj?
	public boolean isEmpty(); //Is Holder empty?
}
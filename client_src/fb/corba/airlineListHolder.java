package fb.corba;


/**
* fb/corba/airlineListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from fb.idl
* Monday, April 28, 2014 7:42:10 PM CST
*/

public final class airlineListHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public airlineListHolder ()
  {
  }

  public airlineListHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = fb.corba.airlineListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    fb.corba.airlineListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return fb.corba.airlineListHelper.type ();
  }

}
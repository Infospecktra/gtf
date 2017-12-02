package org.yang.services.dataAccess.genericDAO;

abstract public class GenericDBExcutionDirector implements DBExcutionDirector
{
   protected StorableList list = null;

   protected String conditions = null;
   public void setConditions(String conditions) { this.conditions = conditions; }

   protected Storable template = null;
   public void setTemplate(Storable template) { this.template = template; }
   public Storable getTemplate()
   {
      if(null!=template)
         return template;
      else if(null!=list&&0<list.size())
        return list.get(0);
      return null;
   }

   public GenericDBExcutionDirector()
   {
      list = new StorableList();
   }

   public void addStorable(Storable storable)
   {
      list.add(storable);
   }

   public void addStorableList(StorableList storableList)
   {
      list.addAll(storableList);
   }

   public StorableList getStorableList()
   {
      return list;
   }
}



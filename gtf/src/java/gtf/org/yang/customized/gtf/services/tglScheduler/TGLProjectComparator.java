package org.yang.customized.gtf.services.tglScheduler;

import java.util.Comparator;
import org.yang.customized.gtf.services.dataAccess.Project;

public final class TGLProjectComparator implements Comparator
{
   private boolean isAscending = true;

   public TGLProjectComparator()
   {
      this(true);
   }

   public TGLProjectComparator(boolean isAscending)
   {
      this.isAscending = isAscending;
   }

   public int compare(Object proj1, Object proj2)
   {
      if(null==proj1||null==proj2)
         throw new NullPointerException();

      String  key1 = ((Project)proj1).getName();
      String  key2 = ((Project)proj2).getName();

      return isAscending?key1.compareTo(key2):key2.compareTo(key1);
//System.out.println("[turn" + i + "]" + key1 + " vs. " + key2 + ", isAscending = " + isAscending[i] + ", compare = " + compare);
   }

   public boolean equals(Object comparator)
   {
      return false;
   }
}
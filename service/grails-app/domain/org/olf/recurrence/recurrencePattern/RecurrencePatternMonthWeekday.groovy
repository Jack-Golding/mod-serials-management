package org.olf.recurrence.recurrencePattern

import grails.gorm.MultiTenant

import com.k_int.web.toolkit.refdata.CategoryId
import com.k_int.web.toolkit.refdata.Defaults
import com.k_int.web.toolkit.refdata.RefdataValue


public class RecurrencePatternMonthWeekday extends RecurrencePattern implements MultiTenant<RecurrencePatternMonthWeekday> {
  Integer week // Validated between 1-4 and -1

  @CategoryId(value="RecurrencePattern.Weekday", defaultInternal=true)
  @Defaults(['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'])
  RefdataValue weekday

  static mapping = {
      week column: 'rpmwd_week'
    weekday column: 'rpmwd_weekday_fk'
  }

  static constraints = {
       week nullable: false, validator: { Integer val, RecurrencePattern obj, errors -> 
          if(!(val >= 1 && val <= 4)){
              errors.rejectValue('week', 'recurrence.pattern.value.not.in.range', ['Week', 1, 4] as Object[], 'Invalid week')
          }
         }
    weekday nullable: false
  }
}

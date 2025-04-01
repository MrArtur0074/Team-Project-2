import React, { useState } from "react";
import { Calendar } from 'primereact/calendar';

export default function InlineDoc() {
  const [date] = useState(null);
  
  return (
    <div className="p-calendar">
      <Calendar value={date} inline showWeek   />
    </div>
  )
}

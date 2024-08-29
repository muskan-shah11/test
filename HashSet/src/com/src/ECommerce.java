package com.src;

import java.util.Objects;

public class ECommerce {

	    int id;
		String  name;
		String hashcode;
		
		public ECommerce(int id, String name, String hashcode) {
			super();
			this.id = id;
			this.name = name;
			this.hashcode = hashcode;
		}

		@Override
		public String toString() {
			return "ECommerce [id=" + id + ", name=" + name + ", hashcode=" + hashcode + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(hashcode);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ECommerce other = (ECommerce) obj;
			return Objects.equals(hashcode, other.hashcode);
		}
   
}

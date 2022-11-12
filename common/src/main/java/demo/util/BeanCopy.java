package demo.util;

import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopy {

	public static <T, R> List<R> copyBeanList(List<T> list, Class<R> clazz, String... ignoreProperties) {
		if (list == null) {
			return Collections.emptyList();
		}
		return list.stream().map(e -> copyBean(e, clazz, ignoreProperties)).collect(Collectors.toList());
	}

	public static <R, P> R copyBean(P param, Class<R> clazz, String... ignoreProperties) {
		R res = BeanUtils.instantiateClass(clazz);
		BeanUtils.copyProperties(param, res, ignoreProperties);
		return res;
	}
}

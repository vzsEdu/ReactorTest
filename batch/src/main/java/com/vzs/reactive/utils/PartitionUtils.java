package com.vzs.reactive.utils;

import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class PartitionUtils {

	public static final String START_INDEX_PARAM_NAME = "startIndex"; //시작 인덱스 파라미터 이름
	public static final String END_INDEX_PARAM_NAME = "endIndex"; //종료 인덱스 파라미터 이름

	/**
	 * 인덱스 파티션
	 * @param gridSize
	 * @param lastIndex
	 * @return
	 */
	public static Map<String, ExecutionContext> partition(int gridSize, long lastIndex) {

		Map<String, ExecutionContext> map = new HashMap<String, ExecutionContext>(gridSize);
		int partitionSize = (int) lastIndex / gridSize;

		for (int gridIndex = 0; gridIndex < gridSize; gridIndex++) {
			ExecutionContext context = new ExecutionContext();
			context.put(START_INDEX_PARAM_NAME, gridIndex * partitionSize);

			if (gridIndex + 1 == gridSize) {
				context.put(END_INDEX_PARAM_NAME, (int) lastIndex);
			} else {
				context.put(END_INDEX_PARAM_NAME, ((gridIndex + 1) * partitionSize));
			}
			map.put(String.valueOf(gridIndex), context);
		}

		return map;
	}
}

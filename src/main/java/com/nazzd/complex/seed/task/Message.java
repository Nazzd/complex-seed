package com.nazzd.complex.seed.task;

import com.nazzd.complex.seed.task.unit.request.AbstractUnitReq;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Message<T extends AbstractUnitReq> {

    private Transaction transaction;

    private T reqData;

    private Context context;

    private Context.UnitData unitData;


    @Data
    @NoArgsConstructor
    public static class Transaction {

        private Long requestId;

        private Long responseId;

        private String clientId;

        private Integer serviceType;

        private Integer source;

        private LocalDateTime operateTime;
    }

    @Data
    @NoArgsConstructor
    public static class Context {

        private List<String> unitIdSeq;

        private Integer currentUnitIndex;

        private List<FullUnits> fullUnits;

        private Map<String, UnitData> commonData;

        @Data
        @NoArgsConstructor
        public static class FullUnits {

            private String unitId;

            private String unitName;

            private String cost;

            private String executeCode;
        }

        @Data
        @NoArgsConstructor
        public static class UnitData {

            private String unitId;

            private Object data;
        }

    }
}
